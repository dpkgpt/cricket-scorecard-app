package app.sports.cricket.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerBowlingStat {
    private int runsConceded;
    private int ballsBowled;
    private int maidens;
    private int wicketsTaken;
    private int extras;
}
