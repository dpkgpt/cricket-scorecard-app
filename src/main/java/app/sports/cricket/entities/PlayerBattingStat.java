package app.sports.cricket.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerBattingStat {
    private int runsScored;
    private int ballsFaced;
    private int dotBalls;
    private int fours;
    private int sixes;
    private int singles;
    private int doubles;
    private int triples;
}
