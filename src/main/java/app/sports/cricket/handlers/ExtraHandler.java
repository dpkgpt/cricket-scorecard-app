package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.entities.Ball;
import app.sports.cricket.entities.Inning;

public class ExtraHandler extends Handler {

    public ExtraHandler(HandlerRegistry handlerRegistry) {
        super(handlerRegistry);
        addHandlerToRegistry(HandlerTypes.EXTRA, this);
    }

    @Override
    public void handle(Ball ball, Inning inning) {
        inning.getScorecard().addExtras(ball.getRuns());
    }
}
