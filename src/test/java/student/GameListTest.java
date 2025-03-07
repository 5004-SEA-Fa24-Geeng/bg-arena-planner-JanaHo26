package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;

class GameListTest {
    private Set<BoardGame> games;

    @BeforeEach
    void setUp() {
        games = new LinkedHashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));

    }

    @Test
    void getGameNames() {
        IGameList list = new GameList();
        // add some games
        list.addToList("all", games.stream());

        // check if the list of game names is sorted alphabetically
        List<String> names = list.getGameNames();
        assertEquals(games.size(), names.size());
        assertEquals("Chess", names.get(1));
        assertEquals("Go", names.get(2));
    }

    @Test
    void clear() {
        IGameList list = new GameList();
        // add some games
        list.addToList("all", games.stream());
        assertEquals(8, list.count());

        // clear list
        list.clear();
        assertEquals(0, list.count());
        assertTrue(list.getGameNames().isEmpty());
    }

    @Test
    void count() {
        IGameList list = new GameList();
        // empty at the beginning
        assertEquals(0, list.count());

        // add a game
        list.addToList("1", games.stream());
        assertEquals(1, list.count());

        // add more games
        list.addToList("2", games.stream());
        assertEquals(2, list.count());

        // 0 after clear
        list.clear();
        assertEquals(0, list.count());
    }

    @Test
    void saveGame() throws IOException {
        // Create temp directory
        Path tempDir = Files.createTempDirectory("temp");
        IGameList list = new GameList();

        // Add the first 3 games from the collection
        list.addToList("1-3", games.stream());
        assertEquals(3, list.count());

        // Get the names of the games currently in the list
        List<String> gameNames = list.getGameNames();

        // Save the games to file
        Path gamefile = tempDir.resolve("Savedgames.txt");
        list.saveGame(gamefile.toString());

        // Read the saved file
        List<String> lines = Files.readAllLines(gamefile);

        // Verify the file has 3 lines
        assertEquals(3, lines.size());

        // Check that each game in our list appears in the file
        for (String gameName : gameNames) {
            boolean found = false;
            for (String line : lines) {
                if (line.contains(gameName)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "Game '" + gameName + "' not found in saved file");
        }
    }

    @Test
    void testAddSingleGameToListByIndex() {
        IGameList list = new GameList();
        list.addToList("1", games.stream());
        assertEquals(1, list.count());
        System.out.println(list.getGameNames());
    }

    @Test
    void addRangeOfGamesToList() {
        IGameList list = new GameList();
        list.addToList("1-3", games.stream());
        assertEquals(3, list.count());
        List<String> names = list.getGameNames();
        assertTrue(names.contains("17 days"));
        assertTrue(names.contains("Chess"));
        assertTrue(names.contains("Go"));
    }

    @Test
    void addAllGamesToList() {
        IGameList list = new GameList();
        list.addToList("ADD_ALL", games.stream());
        assertEquals(games.size(), list.count());
    }

    @Test
    void removeGameFromList() {
        IGameList list = new GameList();
        list.addToList("ADD_ALL", games.stream());
        assertEquals(8, list.count());

        // remove by name
        list.removeFromList("Go");
        assertEquals(7, list.count());
        assertFalse(list.getGameNames().contains("Go"));
    }

    @Test
    void removeAllGamesFromList() {
        IGameList list = new GameList();
        list.addToList("ADD_ALL", games.stream());
        assertEquals(8, list.count());

        list.removeFromList("ADD_ALL");
        assertEquals(0, list.count());
    }

    @Test
    void removeOutOfBoundsRange() {
        IGameList list = new GameList();
        list.addToList("ADD_ALL", games.stream());
        assertEquals(8, list.count());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            list.removeFromList("5-10");
        });

        assertEquals("Invalid range", exception.getMessage());
    }

    @Test
    void removeNonExistentGame() {
        IGameList list = new GameList();
        list.addToList("ADD_ALL", games.stream());
        assertEquals(8, list.count());

        // try to remove non existent game
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            list.removeFromList("AAA");
        });

        assertEquals("Game not found", exception.getMessage());
    }



}