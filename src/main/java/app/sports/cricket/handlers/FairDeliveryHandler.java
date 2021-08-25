package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.entities.Ball;
import app.sports.cricket.entities.Inning;

public class FairDeliveryHandler extends Handler {

    public FairDeliveryHandler(HandlerRegistry handlerRegistry) {
        super(handlerRegistry);
        addHandlerToRegistry(HandlerTypes.FAIR, this);
    }

    @Override
    public void handle(Ball ball, Inning inning) {
        inning.getScorecard().addLegalBallsPlayedForPlayer(inning.getOnStrikePlayer());
        inning.getScorecard().addRunsForPlayer(ball.getRuns(), inning.getOnStrikePlayer());
        if (ball.getRunsType() != null) {
            inning.getScorecard().incrementInRunTypeForPlayer(inning.getOnStrikePlayer(), ball.getRunsType());
        }
    }
}
