package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.entities.Ball;
import app.sports.cricket.entities.Inning;

public class StrikeChangeHandler extends Handler {

    public StrikeChangeHandler(HandlerRegistry handlerRegistry) {
        super(handlerRegistry);
        addHandlerToRegistry(HandlerTypes.STRIKE_CHANGE, this);
    }

    @Override
    public void handle(Ball ball, Inning inning) {
        inning.strikeChange();
    }
}
