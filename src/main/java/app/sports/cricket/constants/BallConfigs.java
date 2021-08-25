package app.sports.cricket.constants;

import app.sports.cricket.entities.Ball;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

// this class is just for easy making of example balls. We can have more type of balls.
@AllArgsConstructor
public enum BallConfigs {
    _0(Ball.builder().runs(0).handlers(new ArrayList<HandlerTypes>(){{
        add(HandlerTypes.FAIR);
    }}).runsType(RunsType.DOT).build()),
    _1(Ball.builder().runs(1).handlers(new ArrayList<HandlerTypes>(){{
        add(HandlerTypes.FAIR);
        add(HandlerTypes.STRIKE_CHANGE);
    }}).runsType(RunsType.SINGLE).build()),
    _2(Ball.builder().runs(2).handlers(new ArrayList<HandlerTypes>(){{
        add(HandlerTypes.FAIR);
    }}).runsType(RunsType.DOUBLE).build()),
    _3(Ball.builder().runs(3).handlers(new ArrayList<HandlerTypes>(){{
        add(HandlerTypes.FAIR);
        add(HandlerTypes.STRIKE_CHANGE);
    }}).runsType(RunsType.TRIPLE).build()),
    _4(Ball.builder().runs(4).handlers(new ArrayList<HandlerTypes>(){{
        add(HandlerTypes.FAIR);
    }}).runsType(RunsType.FOUR).build()),
    _6(Ball.builder().runs(6).handlers(new ArrayList<HandlerTypes>(){{
        add(HandlerTypes.FAIR);
    }}).runsType(RunsType.SIX).build()),
    _W(Ball.builder().runs(0).handlers(new ArrayList<HandlerTypes>(){{
        add(HandlerTypes.FAIR);
        add(HandlerTypes.WICKET);
    }}).runsType(RunsType.DOT).build()),
    _Wd(Ball.builder().runs(1).handlers(new ArrayList<HandlerTypes>(){{
        add(HandlerTypes.EXTRA);
    }}).build()),
    _Nb(Ball.builder().runs(1).handlers(new ArrayList<HandlerTypes>(){{
        add(HandlerTypes.EXTRA);
    }}).build());

    private Ball ball;


    public static Ball getBall(String ball) throws IllegalArgumentException {
        ball = "_"+ball;
        for (BallConfigs b: BallConfigs.values()) {
            if(b.name().equals(ball)) {
                return b.ball;
            }
        }
        throw new IllegalArgumentException("Invalid Ball Type");
    }
}
