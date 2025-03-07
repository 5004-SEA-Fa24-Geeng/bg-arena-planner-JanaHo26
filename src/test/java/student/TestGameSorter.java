package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameSorter {
    private List<BoardGame> games;

    @BeforeEach
    void setUp() {
        games = new ArrayList<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
    }

    @Test
    void testSortByNameAscending() {
        // Get comparator for name, ascending
        Comparator<BoardGame> comparator = GameSorter.getComparator(GameData.NAME, true);
        games.sort(comparator);

        assertEquals("17 days", games.get(0).getName());
        assertEquals("Chess", games.get(1).getName());
        assertEquals("Go", games.get(2).getName());
    }

    @Test
    void testSortByNameDescending() {
        // Get comparator for name, descending
        Comparator<BoardGame> comparator = GameSorter.getComparator(GameData.NAME, false);
        games.sort(comparator);

        assertEquals("Go", games.get(0).getName());
        assertEquals("Chess", games.get(1).getName());
        assertEquals("17 days", games.get(2).getName());
    }

    @Test
    void testSortByRatingDescending() {
        // Get comparator for rating, descending
        Comparator<BoardGame> comparator = GameSorter.getComparator(GameData.RATING, false);
        games.sort(comparator);

        assertEquals("Chess", games.get(0).getName());  // 10.0 rating
        assertEquals("17 days", games.get(1).getName()); // 9.0 rating
        assertEquals("Go", games.get(2).getName());    // 7.5 rating
    }

    @Test
    void testSortByYearDescending() {
        // Get comparator for year, descending
        Comparator<BoardGame> comparator = GameSorter.getComparator(GameData.YEAR, false);
        games.sort(comparator);

        assertEquals("Chess", games.get(0).getName());    // 2006
        assertEquals("17 days", games.get(1).getName());  // 2005
        assertEquals("Go", games.get(2).getName());       // 2000
    }

}


