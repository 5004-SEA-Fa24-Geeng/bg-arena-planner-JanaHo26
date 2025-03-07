package student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.IOException;

/**
 * This class provides functionality to add, remove, clear, and save board games.
 */
public class GameList implements IGameList {
    /**
     * A set of board games stored in the collection.
     */
    private Set<BoardGame> listOfGames;

    /**
     * Constructor for the GameList.
     */
    public GameList() {
        listOfGames = new HashSet<>();
    }

    /**
     * Retrieves the names of all games in the collection.
     *
     * @return a list of game names sorted in case-insensitive alphabetical order.
     */
    @Override
    public List<String> getGameNames() {
        return listOfGames.stream()
                .map(BoardGame::getName)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }

    /**
     * Removes all games from the collection.
     */
    @Override
    public void clear() {
        listOfGames.clear();
    }

    /**
     * Returns the number of games.
     *
     * @return the number of games in this game list.
     */
    @Override
    public int count() {
        return listOfGames.size();
    }

    /**
     * Saves the current collection of games to a file.
     *
     * @param filename The name of the file to save the list to.
     * @throws IllegalArgumentException If the filename is null or empty.
     * @throws RuntimeException if an error occurs while writing to the file.
     */
    @Override
    public void saveGame(String filename) {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (BoardGame game : listOfGames) {
                writer.write(game.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not save to file: " + filename + " - " + e.getMessage());
        }
    }

    /**
     * Adds one or more games from the filtered stream to the collection.
     * Games can be added by index, by name, or as a range of indices.
     *
     * @param str      the string to parse and add games to the list.
     * @param filtered the filtered list to use as a basis for adding.
     * @throws IllegalArgumentException if the input string is invalid,
     *                                  the filtered list is empty,
     *                                  or the specified game cannot be found.
     */
    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }

        str = str.trim().toLowerCase();
        List<BoardGame> filteredList = filtered.collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            throw new IllegalArgumentException("Empty list");
        }

        if (str.equalsIgnoreCase("add_all") || str.equals("all")) {
            listOfGames.addAll(filteredList);
            return;
        }

        if (str.contains("-")) {
            String[] range = str.split("-");
            if (range.length != 2) {
                throw new IllegalArgumentException("Invalid range");
            }

            try {
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);

                if (start < 1 || end < 1 || start > filteredList.size() || end > filteredList.size() || start > end) {
                    throw new IllegalArgumentException("Invalid range");
                }

                for (int i = start; i <= end; i++) {
                    listOfGames.add(filteredList.get(i - 1)); // Adjust for 0-based index
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid range");
            }
            return;
        }

        try {
            int index = Integer.parseInt(str);

            if (index < 1 || index > filteredList.size()) {
                throw new IllegalArgumentException("Invalid range");
            }

            listOfGames.add(filteredList.get(index - 1));
            return;
        } catch (NumberFormatException e) {
            boolean found = false;
            for (BoardGame game : filteredList) {
                if (game.getName().trim().equalsIgnoreCase(str)) {
                    listOfGames.add(game);
                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new IllegalArgumentException("Game not found");
            }
        }
    }

    /**
     * Removes one or more games from the collection.
     *
     * @param str The string to parse and remove games from the list.
     * @throws IllegalArgumentException if the input string is invalid, the game list is empty,
     *                          or the specified game cannot be found.
     */
    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }

        str = str.trim().toLowerCase();

        if (listOfGames.isEmpty()) {
            throw new IllegalArgumentException("Game list is empty");
        }

        if (str.equalsIgnoreCase("add_all") || str.equals("all")) {
            clear();
            return;
        }

        List<BoardGame> gamesList = new ArrayList<>(listOfGames);
        gamesList.sort(Comparator.comparing(BoardGame::getName));

        if (str.contains("-")) {
            String[] range = str.split("-");
            if (range.length != 2) {
                throw new IllegalArgumentException("Invalid range");
            }

            try {
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);

                if (start < 1 || end < 1 || start > gamesList.size() || end > gamesList.size() || start > end) {
                    throw new IllegalArgumentException("Invalid range");
                }

                Set<BoardGame> toRemove = new HashSet<>();
                for (int i = start; i <= end; i++) {
                    toRemove.add(gamesList.get(i - 1));
                }
                listOfGames.removeAll(toRemove);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid range");
            }
            return;
        }

        try {
            int index = Integer.parseInt(str);
            if (index < 1 || index > gamesList.size()) {
                throw new IllegalArgumentException("Invalid range");
            }
            listOfGames.remove(gamesList.get(index - 1));
            return;
        } catch (NumberFormatException e) {
            boolean found = false;
            for (BoardGame game : new ArrayList<>(listOfGames)) {
                if (game.getName().trim().equalsIgnoreCase(str)) {
                    listOfGames.remove(game);
                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new IllegalArgumentException("Game not found");
            }
        }
    }
}

