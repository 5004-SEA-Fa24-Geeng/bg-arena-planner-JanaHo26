package student;

import java.util.Comparator;

/**
 * Provides sorting strategies for BoardGame objects, and implements the Strategy pattern for different sorting methods.
 */
public final class GameSorter {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private GameSorter() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Gets a comparator for the specified field and direction.
     *
     * @param sortOn the field to sort on
     * @param ascending true for ascending order, false for descending
     * @return a comparator for the specified field and direction
     */
    public static Comparator<BoardGame> getComparator(GameData sortOn, boolean ascending) {
        Comparator<BoardGame> comparator = getBaseComparator(sortOn);

        if (!ascending) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    /**
     * Gets a base comparator for the specified field (ascending).
     *
     * @param sortOn the field to sort on
     * @return a base comparator for the specified field
     */
    private static Comparator<BoardGame> getBaseComparator(GameData sortOn) {
        switch (sortOn) {
            case NAME:
                return Comparator.comparing(BoardGame::getName, String.CASE_INSENSITIVE_ORDER);
            case MIN_PLAYERS:
                return Comparator.comparingInt(BoardGame::getMinPlayers);
            case MAX_PLAYERS:
                return Comparator.comparingInt(BoardGame::getMaxPlayers);
            case MIN_TIME:
                return Comparator.comparingInt(BoardGame::getMinPlayTime);
            case MAX_TIME:
                return Comparator.comparingInt(BoardGame::getMaxPlayTime);
            case DIFFICULTY:
                return Comparator.comparingDouble(BoardGame::getDifficulty);
            case RATING:
                return Comparator.comparingDouble(BoardGame::getRating);
            case RANK:
                return Comparator.comparingInt(BoardGame::getRank);
            case YEAR:
                return Comparator.comparingInt(BoardGame::getYearPublished);
            default:
                return Comparator.comparing(BoardGame::getName, String.CASE_INSENSITIVE_ORDER);
        }
    }
}
