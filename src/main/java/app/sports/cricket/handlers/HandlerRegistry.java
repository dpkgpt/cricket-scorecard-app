package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;

import java.util.HashMap;
import java.util.Map;

public class HandlerRegistry {
    private Map<HandlerTypes, Handler> handlers;

    public HandlerRegistry() {
        this.handlers = new HashMap<>();
    }
    void addToRegistry(HandlerTypes handlerType, Handler handler) {
        this.handlers.put(handlerType,handler);
    }

    public Handler getHandler(HandlerTypes handlerType) {
        return this.handlers.get(handlerType);
    }
}
