package app.sports.cricket.entities;

import app.sports.cricket.utils.Utils;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class Inning {
    private Team battingTeam;
    private Team bowlingTeam;
    private Scorecard scorecard;
    private int totalOvers;
    private Player onStrikePlayer;
    private Player nonStrikePlayer;
    private Player currentBowler;
    private boolean isInningEnded;
    public Inning(int totalOvers, Team battingTeam, Team bowlingTeam) {
        this.totalOvers = totalOvers;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        scorecard = Scorecard.builder()
                .battingTeamStats(getStatsMapForPlayers(battingTeam.getPlayers(),PlayerBattingStat.class))
                .bowlingTeamStats(new HashMap<>())
                .build();
        this.onStrikePlayer = battingTeam.getPlayers().get(0);
        this.nonStrikePlayer = battingTeam.getPlayers().get(1);
    }

    private<T> Map<Player, T> getStatsMapForPlayers(List<Player> players, Class<T> tClass) {
        Map<Player, T> statsMap = new LinkedHashMap<>();
        players.forEach(p-> {
            try {
                statsMap.put(p, tClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return statsMap;
    }

    public void strikeChange() {
        Player p = getOnStrikePlayer();
        setOnStrikePlayer(getNonStrikePlayer());
        setNonStrikePlayer(p);
    }

    public void displayScoreCard() {
        Utils.prettyPrintScoreCard(this);
        System.out.println(this.scorecard.getBowlerStat(currentBowler));
    }

    public boolean validateInningState() {
        if(scorecard.getWickets() == battingTeam.getTotalPlayers()-1) {
            System.out.println("Innings over...");
            isInningEnded = true;
        }
        if(totalOvers == Utils.getTotalOverBalled(scorecard.getLegalBallsBalled())) {
            System.out.println("Innings over...");
            isInningEnded = true;
        }
        return isInningEnded;
    }
}
