package app.sports.cricket.entities;

import app.sports.cricket.constants.BallConfigs;
import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.handlers.ExtraHandler;
import app.sports.cricket.handlers.FairDeliveryHandler;
import app.sports.cricket.handlers.Handler;
import app.sports.cricket.handlers.HandlerRegistry;
import app.sports.cricket.handlers.StrikeChangeHandler;
import app.sports.cricket.handlers.WicketHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Game {
    private int numOfOvers;
    private List<Inning> innings;
    private Team team1;
    private Team team2;
    private HandlerRegistry handlerRegistry;
    private int leadBy;
    private Inning currentInning;

    public Game(int numOfOvers, Team team1, Team team2) {
        this.numOfOvers = numOfOvers;
        this.team1 = team1;
        this.team2 = team2;
        this.innings = new LinkedList<>();
        this.leadBy = -1;
        this.handlerRegistry = new HandlerRegistry();
        new ExtraHandler(handlerRegistry);
        new FairDeliveryHandler(handlerRegistry);
        new StrikeChangeHandler(handlerRegistry);
        new WicketHandler(handlerRegistry);
    }

    private void playOver(List<String> over) {
        int totalLegalBallsBalledInOver = 0;
        int totalBallsBalled = 0;
        for (String b : over) {
            Ball ball = BallConfigs.getBall(b);
            if(ball.handlers.contains(HandlerTypes.FAIR)) {
                totalLegalBallsBalledInOver ++;
            }
            totalBallsBalled ++;
            System.out.println(ball);
            ball.handlers.forEach(handlerType -> handlerRegistry.getHandler(handlerType).handle(ball, currentInning));
            if(currentInning.validateInningState() || totalLegalBallsBalledInOver == 6) {
                break;
            }
        }
        if(totalBallsBalled != over.size() || (!currentInning.isInningEnded() && totalLegalBallsBalledInOver != 6)) {
            throw new RuntimeException("Invalid over given");
        }
        currentInning.displayScoreCard();
        currentInning.strikeChange();
    }

    private void playInning(List<List<String>> overs, Team team1, Team team2) {
        currentInning = new Inning(this.numOfOvers, team1,team2);
        innings.add(currentInning);
        for (List<String> over : overs) {
            if (!currentInning.isInningEnded())
                playOver(over);
            else
                break;
        }
    }

    public void playGame(List<List<String>> oversForA, List<List<String>> oversForB) {
        playInning(oversForA,team1,team2);
        leadBy = currentInning.getScorecard().getTotalRuns();
        playInning(oversForB,team2,team1);
        if(leadBy+1 < currentInning.getScorecard().getTotalRuns()) {
            System.out.println(team2.getName() + " Won the Match with " + (team1.getTotalPlayers() - currentInning.getScorecard().getWickets()) + " Wickets remaining...");
        } else if(leadBy > currentInning.getScorecard().getTotalRuns()) {
            System.out.println(team1.getName() + " Won the Match by " + (leadBy - currentInning.getScorecard().getTotalRuns()) + " runs..." );
        } else {
            System.out.println("Match is Drawn");
        }
    }

}
