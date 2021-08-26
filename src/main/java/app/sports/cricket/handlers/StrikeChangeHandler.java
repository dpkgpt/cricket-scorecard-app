package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.entities.Ball;
import app.sports.cricket.entities.Inning;
import app.sports.cricket.entities.Player;

public class StrikeChangeHandler extends Handler {

    public StrikeChangeHandler(HandlerRegistry handlerRegistry) {
        super(HandlerTypes.STRIKE_CHANGE, handlerRegistry);
    }

    @Override
    public void handle(Ball ball, Inning inning, Player bowler) {
        inning.strikeChange();
    }
}
