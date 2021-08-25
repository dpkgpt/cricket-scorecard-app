package app.sports.cricket.utils;

import app.sports.cricket.entities.Inning;

public class Utils {
    private static final String PLAYER_NAME_TITLE   = "Player Name";
    public static final int PLAYER_NAME_SPACE = 20;
    public static final String SCORE_TITLE = "Score";
    public static final int SCORE_SPACE = 5;
    public static final int RUNS_DESC_SPACE = 3;
    public static final String BALLS_TITLE = "Balls Faced";
    public static final String DELIM = "\t";


    public static void prettyPrintScoreCard(Inning inning) {
        StringBuilder scardStringBuilder = new StringBuilder("============ Scorecard for team: " + inning.getBattingTeam().getName() + " ================\n");
        appendToStringBuilderWithSpaces(scardStringBuilder,PLAYER_NAME_TITLE , PLAYER_NAME_SPACE, DELIM );
        appendToStringBuilderWithSpaces(scardStringBuilder, SCORE_TITLE, SCORE_SPACE, DELIM);
        appendToStringBuilderWithSpaces(scardStringBuilder, "4s", RUNS_DESC_SPACE, DELIM);
        appendToStringBuilderWithSpaces(scardStringBuilder, "6s", RUNS_DESC_SPACE, DELIM);
        appendToStringBuilderWithSpaces(scardStringBuilder, BALLS_TITLE, 0,"\n");

        inning.getScorecard().getBattingTeamStats().forEach((p,stats)-> {
            boolean isPlayerPlaying = p == inning.getOnStrikePlayer() || p == inning.getNonStrikePlayer();
            String name = p.getName()+ (isPlayerPlaying ? "*" : "");
            appendToStringBuilderWithSpaces(scardStringBuilder, name, PLAYER_NAME_SPACE, DELIM );
            appendToStringBuilderWithSpaces(scardStringBuilder, stats.getRunsScored(), SCORE_SPACE, DELIM);
            appendToStringBuilderWithSpaces(scardStringBuilder, stats.getFours(), RUNS_DESC_SPACE, DELIM);
            appendToStringBuilderWithSpaces(scardStringBuilder, stats.getSixes(), RUNS_DESC_SPACE, DELIM);
            appendToStringBuilderWithSpaces(scardStringBuilder, stats.getBallsFaced(), 0,"\n");
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
        if(toAppend instanceof String) {
            stringBuilder.append(toAppend).append(getSpaces((String) toAppend,spaceNeeded));
        } else if (toAppend instanceof Integer) {
            stringBuilder.append(toAppend).append(getSpaces(Integer.toString((Integer) toAppend),spaceNeeded));
        } else if (toAppend instanceof Double) {
            stringBuilder.append(toAppend).append(getSpaces(Double.toString((Double) toAppend),spaceNeeded));
        }
        if(delim!= null) {
            stringBuilder.append(delim);
        }
    }

    private static String getSpaces(String s, int spaceNeeded) {
        if(spaceNeeded < s.length()) {
            return "";
        }
        StringBuilder spaces = new StringBuilder();
        for(int i = 0; i < spaceNeeded-s.length(); i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public static float getTotalOverBalled(int legalBallsBalled) {
        return legalBallsBalled/6 + (legalBallsBalled%6)*0.1f;
    }
}
