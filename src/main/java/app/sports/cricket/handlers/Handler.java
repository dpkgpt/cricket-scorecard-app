package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.entities.Ball;
import app.sports.cricket.entities.Inning;

public abstract class Handler {
    HandlerRegistry handlerRegistry;

    public Handler(HandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    public abstract void handle(Ball ball, Inning inning);

    public void addHandlerToRegistry(HandlerTypes handlerType, Handler handler) {
        this.handlerRegistry.addToRegistry(handlerType,handler);
    }
}
