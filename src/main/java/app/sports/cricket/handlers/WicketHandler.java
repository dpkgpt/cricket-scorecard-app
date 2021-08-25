package app.sports.cricket.handlers;

import app.sports.cricket.constants.HandlerTypes;
import app.sports.cricket.entities.Ball;
import app.sports.cricket.entities.Inning;

public class WicketHandler extends Handler {

    public WicketHandler(HandlerRegistry handlerRegistry) {
        super(handlerRegistry);
        addHandlerToRegistry(HandlerTypes.WICKET, this);
    }

    @Override
    public void handle(Ball ball, Inning inning) {
        inning.getScorecard().addWicket();
        if(inning.getScorecard().getWickets() < inning.getBattingTeam().getTotalPlayers()-1) {
            inning.setOnStrikePlayer(inning.getBattingTeam().getPlayers().get(inning.getScorecard().getWickets()+1));
        } else {
            inning.setOnStrikePlayer(null);
        }
    }
}
