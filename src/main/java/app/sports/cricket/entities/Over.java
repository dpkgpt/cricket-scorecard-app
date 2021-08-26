package app.sports.cricket.entities;

import app.sports.cricket.constants.BallConfigs;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Over {
    private Player bowler;
    private List<Ball> balls;

    @Builder
    public Over(Player bowler, List<String> balls) {
        this.bowler = bowler;
        this.balls = balls.stream().map(BallConfigs::getBall).collect(Collectors.toCollection(LinkedList::new));
    }
}
