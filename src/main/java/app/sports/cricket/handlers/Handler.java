package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.entities.Ball;
import app.sports.cricket.entities.Inning;
import app.sports.cricket.entities.Player;

public abstract class Handler {

    public Handler(HandlerTypes handlerType, HandlerRegistry handlerRegistry) {
        handlerRegistry.addToRegistry(handlerType,this);
    }

    public abstract void handle(Ball ball, Inning inning, Player bowler);
}
