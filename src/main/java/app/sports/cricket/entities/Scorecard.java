package app.sports.cricket.entities;

import app.sports.cricket.constants.RunsType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class Scorecard {
    private int legalBallsBalled;
    private int totalRuns;
    private int extras;
    private int wickets;
    private Map<Player, PlayerBattingStat> battingTeamStats;
    private Map<Player, PlayerBowlingStat> bowlingTeamStats;


    public void addRunsForPlayer(int runs, Player battingPlayer) {
        PlayerBattingStat battingPlayerStat = this.battingTeamStats.get(battingPlayer);
        battingPlayerStat.setRunsScored(battingPlayerStat.getRunsScored() + runs);
        this.totalRuns += runs;
    }

    public void addLegalBallsPlayedForPlayer(Player player) {
        PlayerBattingStat battingPlayerStat = this.battingTeamStats.get(player);
        battingPlayerStat.setBallsFaced(battingPlayerStat.getBallsFaced() + 1);
        this.legalBallsBalled += 1;
    }

    public void addExtras(int run) {
        this.totalRuns += run;
        this.extras += run;
    }

    public void incrementInRunTypeForPlayer(Player player, RunsType runsType) {
        PlayerBattingStat battingStat = battingTeamStats.get(player);
        switch (runsType) {
            case DOT: {
                battingStat.setDotBalls(battingStat.getDotBalls() + 1);
                break;
            }
            case SINGLE: {
                battingStat.setSingles(battingStat.getSingles() + 1);
                break;
            }
            case DOUBLE: {
                battingStat.setDoubles(battingStat.getDoubles() + 1);
                break;
            }
            case TRIPLE: {
                battingStat.setTriples(battingStat.getTriples() + 1);
                break;
            }
            case FOUR: {
                battingStat.setFours(battingStat.getFours() + 1);
                break;
            }
            case SIX: {
                battingStat.setSixes(battingStat.getSixes() + 1);
                break;
            }
        }
    }


    public void addWicket() {
        this.wickets += 1;
    }
}
