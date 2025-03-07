package student;

public final class Filters {
    private Filters() {
    }


    /**
     * Main filter method that routes to the appropriate filter method based on column type.
     *
     * @param game the board game to check.
     * @param column the column to filter on.
     * @param op the operation to perform.
     * @param value the value to filter against.
     * @return true if the game matches the filter, false otherwise.
     */
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

    /**
     * Filters string data based on the specified operation.
     *
     * @param gameData the string data to filter.
     * @param op the operation to perform.
     * @param value the value to filter against.
     * @return true if the string data matches the filter, false otherwise.
     */
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

    /**
     * Filters numeric integer data based on the specified operation.
     *
     * @param gameData the integer data to filter.
     * @param op the operation to perform.
     * @param value the value to filter against.
     * @return true if the integer data matches the filter, false otherwise.
     */
    public static boolean filterInt(int gameData, Operations op, String value) {
        try {
            int numValue = Integer.parseInt(value);

            switch (op) {
                case GREATER_THAN_EQUALS:  // first check >=
                    return gameData >= numValue;
                case LESS_THAN_EQUALS:   // first check <=
                    return gameData <= numValue;
                case GREATER_THAN:    // then check >
                    return gameData > numValue;
                case LESS_THAN:     // then check
                    return gameData < numValue;
                case EQUALS:
                    return gameData == numValue;
                case NOT_EQUALS:
                    return gameData != numValue;
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Filters double-precision floating point data based on the specified operation.
     *
     * @param gameData the double data to filter.
     * @param op the operation to perform.
     * @param value the value to filter against.
     * @return true if the double data matches the filter, false otherwise.
     */
    public static boolean filterDouble(double gameData, Operations op, String value) {
        try {
            double numValue = Double.parseDouble(value);

            switch (op) {
                case GREATER_THAN_EQUALS:
                    return gameData >= numValue;
                case LESS_THAN_EQUALS:
                    return gameData <= numValue;
                case GREATER_THAN:
                    return gameData > numValue;
                case LESS_THAN:
                    return gameData < numValue;
                case EQUALS:
                    return gameData == numValue;
                case NOT_EQUALS:
                    return gameData != numValue;
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
