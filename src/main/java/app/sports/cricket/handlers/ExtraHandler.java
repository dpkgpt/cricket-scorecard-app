package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.entities.Ball;
import app.sports.cricket.entities.Inning;
import app.sports.cricket.entities.Player;

public class ExtraHandler extends Handler {

    public ExtraHandler(HandlerRegistry handlerRegistry) {
        super(HandlerTypes.EXTRA, handlerRegistry);
    }

    @Override
    public void handle(Ball ball, Inning inning, Player bowler) {
        inning.getScorecard().addExtras(ball.getRuns(), bowler);
    }
}
