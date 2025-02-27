package student;

public final class Filters {
    private Filters() {
    }

    public static boolean filter(BoardGame game, GameData column,
                                 Operations op, String value) {

        switch (column) {
            case NAME:
                //filter the name
                return filterString(game.getName(), op, value);
            case MIN_PLAYERS:
                return filterInt(game.getMinPlayers(), op, value);
            case MAX_PLAYERS:
                return filterInt(game.getMaxPlayers(), op, value);
            case MIN_TIME:
                return filterInt(game.getMinPlayTime(), op, value);
            case MAX_TIME:
                return filterInt(game.getMaxPlayTime(), op, value);
            case DIFFICULTY:
                return filterDouble(game.getDifficulty(), op, value);
            case RATING:
                return filterDouble(game.getRating(), op, value);
            case RANK:
                return filterInt(game.getRank(), op, value);
            case YEAR:
                return filterInt(game.getYearPublished(), op, value);
            default:
                return false;
        }
    }

    public static boolean filterString(String gameData, Operations op, String value) {
        if (gameData == null || value == null) {
            return false;
        }

        switch (op) {
            case EQUALS:
                return gameData.equalsIgnoreCase(value);
            case NOT_EQUALS:
                return !gameData.equalsIgnoreCase(value);
            case CONTAINS:
                return gameData.toLowerCase().contains(value.toLowerCase());
            default:
                return false;
        }

    }

    public static boolean filterInt(int gameData, Operations op, String value) {
        try {
            int numValue = Integer.parseInt(value);

            switch (op) {
                case EQUALS:
                    return gameData == numValue;
                case NOT_EQUALS:
                    return gameData != numValue;
                case GREATER_THAN:
                    return gameData > numValue;
                case LESS_THAN:
                    return gameData < numValue;
                case GREATER_THAN_EQUALS:
                    return gameData >= numValue;
                case LESS_THAN_EQUALS:
                    return gameData <= numValue;
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean filterDouble(double gameData, Operations op, String value) {
        try {
            double numValue = Double.parseDouble(value);

            switch (op) {
                case EQUALS:
                    return gameData == numValue;
                case NOT_EQUALS:
                    return gameData != numValue;
                case GREATER_THAN:
                    return gameData > numValue;
                case LESS_THAN:
                    return gameData < numValue;
                case GREATER_THAN_EQUALS:
                    return gameData >= numValue;
                case LESS_THAN_EQUALS:
                    return gameData <= numValue;
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
