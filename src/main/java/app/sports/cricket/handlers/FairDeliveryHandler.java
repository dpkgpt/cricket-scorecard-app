package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.entities.Ball;
import app.sports.cricket.entities.Inning;
import app.sports.cricket.entities.Player;

public class FairDeliveryHandler extends Handler {

    public FairDeliveryHandler(HandlerRegistry handlerRegistry) {
        super(HandlerTypes.FAIR,handlerRegistry);
    }

    @Override
    public void handle(Ball ball, Inning inning, Player bowler) {
        inning.getScorecard().addLegalBallsPlayedForPlayer(inning.getOnStrikePlayer(), bowler);
        inning.getScorecard().addRunsForPlayer(ball.getRuns(), inning.getOnStrikePlayer(), bowler);
        if (ball.getRunsType() != null) {
            inning.getScorecard().incrementInRunTypeForPlayer(inning.getOnStrikePlayer(), ball.getRunsType());
        }
    }
}
