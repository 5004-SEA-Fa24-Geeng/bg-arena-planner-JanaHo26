package student;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * JUnit test for the Planner class.
 * 
 * Just a sample test to get you started, also using
 * setup to help out. 
 */
public class TestPlanner {
    private static Set<BoardGame> games;
    private static IPlanner planner;

    @BeforeAll
    public static void setup() {
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));

        planner = new Planner(games);
    }

    @Test
    public void testFilterName() {
        List<BoardGame> filtered = planner.filter("name == Go").toList();
        assertEquals(1, filtered.size());
        assertEquals("Go", filtered.get(0).getName());
    }

    @Test
    public void testNamesContainingGo() {
        List<BoardGame> allGames = planner.filter("").toList();
        int goGameCount = 0;
        for (BoardGame game : allGames) {
            if (game.getName().toLowerCase().contains("go")) {
                goGameCount++;
            }
        }
        assertEquals(4, goGameCount);
    }


    @Test
    public void testFilterMinPlayers() {
        List<BoardGame> allGames = planner.filter("").toList();
        int count = 0;
        for (BoardGame game : allGames) {
            if (game.getMinPlayers() >= 5) {
                count++;
            }
        }
        assertEquals(3, count);
    }


    @Test
    public void testFilterMaxPlayers() {
        List<BoardGame> allGames = planner.filter("").toList();
        int count = 0;
        for (BoardGame game : allGames) {
            if (game.getMaxPlayers() <= 5) {
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void testFilterMinTime() {
        List<BoardGame> allGames = planner.filter("").toList();
        int count = 0;
        for (BoardGame game : allGames) {
            if (game.getMinPlayTime() <= 50) {
                count++;
            }
        }
        assertEquals(6, count);
    }

    @Test
    public void testFilterMaxTime() {
        List<BoardGame> allGames = planner.filter("").toList();
        int count = 0;
        for (BoardGame game : allGames) {
            if (game.getMaxPlayTime() > 100) {
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void testFilterDifficulty() {
        List<BoardGame> allGames = planner.filter("").toList();
        int count = 0;
        for (BoardGame game : allGames) {
            if (game.getDifficulty() >= 8.0) {
                count++;
            }
        }
        assertEquals(3, count);
    }


    @Test
    public void testFilterRating() {
        List<BoardGame> allGames = planner.filter("").toList();
        int count = 0;
        for (BoardGame game : allGames) {
            if (game.getRating() >= 9.0) {
                count++;
            }
        }
        assertEquals(3, count);
    }

    @Test
    public void testFilterYear() {
        List<BoardGame> allGames = planner.filter("").toList();
        int count = 0;
        for (BoardGame game : allGames) {
            if (game.getYearPublished() < 2003) {
                count++;
            }
        }
        assertEquals(3, count);
    }


    @Test
    public void testSort() {
        List<BoardGame> filtered = planner.filter("", GameData.YEAR, false).toList();
        assertEquals(8, filtered.size());
        assertEquals("Monopoly", filtered.get(0).getName());  // 2007
        assertEquals("Chess", filtered.get(1).getName());     // 2006
        assertEquals("17 days", filtered.get(2).getName());   // 2005
    }

    @Test
    void testReset() {
        planner.filter("name==Go");
        List<BoardGame> filtered1 = planner.filter("").toList();
        assertTrue(filtered1.size() < 8);

        planner.reset();
        List<BoardGame> filtered2 = planner.filter("").toList();
        assertEquals(8, filtered2.size());
    }

    @Test
    void testSortWithNameAscending() {
        List<BoardGame> filtered = planner.filter("", GameData.NAME, true).toList();
        assertEquals(8, filtered.size());
    }
}