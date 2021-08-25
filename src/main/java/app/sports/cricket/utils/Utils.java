package app.sports.cricket.utils;

import app.sports.cricket.entities.Inning;

import java.text.DecimalFormat;

public class Utils {
    private static final String PLAYER_NAME_TITLE = "Player Name";
    private static final int PLAYER_NAME_SPACE = 20;
    private static final String SCORE_TITLE = "Score";
    private static final int SCORE_SPACE = 5;
    private static final int RUNS_DESC_SPACE = 3;
    private static final String BALLS_TITLE = "Balls Faced";
    private static final String DELIM = "\t";
    private static final String STRIKE_RATE_TITLE = "Strike Rate";
    private static final int BALLS_SPACE = 15;
    private static final DecimalFormat df=new DecimalFormat("#.###");


    public static void prettyPrintScoreCard(Inning inning) {
        StringBuilder scardStringBuilder = new StringBuilder("============ Scorecard for team: " + inning.getBattingTeam().getName() + " ================\n");
        appendToStringBuilderWithSpaces(scardStringBuilder, PLAYER_NAME_TITLE, PLAYER_NAME_SPACE, DELIM);
        appendToStringBuilderWithSpaces(scardStringBuilder, SCORE_TITLE, SCORE_SPACE, DELIM);
        appendToStringBuilderWithSpaces(scardStringBuilder, "4s", RUNS_DESC_SPACE, DELIM);
        appendToStringBuilderWithSpaces(scardStringBuilder, "6s", RUNS_DESC_SPACE, DELIM);
        appendToStringBuilderWithSpaces(scardStringBuilder, BALLS_TITLE, BALLS_SPACE, DELIM);
        appendToStringBuilderWithSpaces(scardStringBuilder, STRIKE_RATE_TITLE, 0,"\n");


        inning.getScorecard().getBattingTeamStats().forEach((p, stats) -> {
            boolean isPlayerPlaying = p == inning.getOnStrikePlayer() || p == inning.getNonStrikePlayer();
            String name = p.getName() + (isPlayerPlaying ? "*" : "");
            appendToStringBuilderWithSpaces(scardStringBuilder, name, PLAYER_NAME_SPACE, DELIM);
            appendToStringBuilderWithSpaces(scardStringBuilder, stats.getRunsScored(), SCORE_SPACE, DELIM);
            appendToStringBuilderWithSpaces(scardStringBuilder, stats.getFours(), RUNS_DESC_SPACE, DELIM);
            appendToStringBuilderWithSpaces(scardStringBuilder, stats.getSixes(), RUNS_DESC_SPACE, DELIM);
            appendToStringBuilderWithSpaces(scardStringBuilder, stats.getBallsFaced(), BALLS_SPACE, DELIM);
            appendToStringBuilderWithSpaces(scardStringBuilder, df.format(calculatePlayersStrikeRate(stats.getRunsScored(),stats.getBallsFaced())),0,"\n");
        });

        scardStringBuilder.append("-------------------------------------------------------------------------\n");
        scardStringBuilder.append("Total: ").append(inning.getScorecard().getTotalRuns()).append("/")
                .append(inning.getScorecard().getWickets()).append(DELIM)
                .append("Overs: ").append(getTotalOverBalled(inning.getScorecard().getLegalBallsBalled())).append(DELIM)
                .append("Extras: ").append(inning.getScorecard().getExtras()).append("\n");
        scardStringBuilder.append("=========================================================================");

        System.out.println(scardStringBuilder);
    }

    private static <T> void appendToStringBuilderWithSpaces(StringBuilder stringBuilder, T toAppend, int spaceNeeded, String delim) {
        if (toAppend instanceof String) {
            stringBuilder.append(toAppend).append(getSpaces((String) toAppend, spaceNeeded));
        } else if (toAppend instanceof Integer) {
            stringBuilder.append(toAppend).append(getSpaces(Integer.toString((Integer) toAppend), spaceNeeded));
        } else if (toAppend instanceof Double) {
            stringBuilder.append(toAppend).append(getSpaces(Double.toString((Double) toAppend), spaceNeeded));
        }
        if (delim != null) {
            stringBuilder.append(delim);
        }
    }

    private static double calculatePlayersStrikeRate(int runs, int ballsPlayed) {
        if(ballsPlayed == 0) {
            return 0;
        }
        return ((runs*1.0)/ballsPlayed)*100;
    }

    private static String getSpaces(String s, int spaceNeeded) {
        if (spaceNeeded < s.length()) {
            return "";
        }
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spaceNeeded - s.length(); i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public static float getTotalOverBalled(int legalBallsBalled) {
        return legalBallsBalled / 6 + (legalBallsBalled % 6) * 0.1f;
    }
}
