package student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.IOException;

public class GameList implements IGameList {
    private Set<BoardGame> listOfGames;

    /**
     * Constructor for the GameList.
     */
    public GameList() {
        listOfGames = new HashSet<>();
    }

    @Override
    public List<String> getGameNames() {
        // convert a collection of games into a list of names, sorted alphabetically
        return listOfGames.stream()
                .map(BoardGame::getName)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        listOfGames.clear();
    }

    @Override
    public int count() {
        return listOfGames.size();
    }



    @Override
    public void saveGame(String filename) {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // write all games into the file
            for (BoardGame game : listOfGames) {
                writer.write(game.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not save game", e);
        }
    }



    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }

        // remove blank and turn to lower case
        str = str.trim().toLowerCase();
        List<BoardGame> filteredList = filtered.collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            throw new IllegalArgumentException("Empty list");
        }

        // if the string added is "ADD_ALL", add all games and end the function
        if (str.equals(ADD_ALL)) {
            listOfGames.addAll(filteredList);
            return;
        }

        // for example, 1-3 will become ["1","3"]
        // check if it's range input
        if (str.contains("-")) {
            String[] range = str.split("-");
            if (range.length != 2) {
                throw new IllegalArgumentException("Invalid range");
            }

            try {
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);

                if (start < 1 || end < 1 || start > filteredList.size()) {
                    throw new IllegalArgumentException("Invalid range");
                }

                for (int i = start; i <= end; i++) {
                    listOfGames.add(filteredList.get(i -1)); // i-1 because list start from 0
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid range");
            }
            return;
        }

        // 'str' as if it's a single index like "2"
        try {
            int index = Integer.parseInt(str);

            if (index < 1 || index > filteredList.size()) {
                throw new IllegalArgumentException("Invalid range");
            }

            listOfGames.add(filteredList.get(index - 1));
            return;
        } catch (NumberFormatException e) {
            // not number, try to search by name
            boolean found = false;
            for (BoardGame game : filteredList) {
                if (game.getName().toLowerCase().equals(str)) {
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

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }

        str = str.trim().toLowerCase();

        if (listOfGames.isEmpty()) {
            throw new IllegalArgumentException("Game list is empty");
        }

        if (str.equals(ADD_ALL)) {
            clear();
            return;
        }

        // make sure the index won't be influenced by the original order of listOfGames
        List<BoardGame> gameslist = new ArrayList<>(listOfGames);
        gameslist.sort((g1, g2) -> g1.getName().compareTo(g2.getName()));

        // range like "1-3"
        if (str.contains("-")) {
            String[] range = str.split("-");
            if (range.length != 2) {
                throw new IllegalArgumentException("Invalid range");
            }

            try {
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);   // convert a range string into a number

                if (start < 1 || end < 1 || start > gameslist.size() || end > gameslist.size() || start > end) {
                    throw new IllegalArgumentException("Invalid range");
                }

                Set<BoardGame> toRemove = new HashSet<>();
                for (int i = start; i <= end; i++) {
                    toRemove.add(gameslist.get(i - 1));
                }
                listOfGames.removeAll(toRemove);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid range");
            }
            return;
        }

        // single index
        try {
            int index = Integer.parseInt(str);
            if (index < 1 || index > gameslist.size()) {
                throw new IllegalArgumentException("Invalid range");
            }
            listOfGames.remove(gameslist.get(index - 1));
            return;
        } catch (NumberFormatException e) {
            // not a number, search by name
            boolean found = false;
            for (BoardGame game : new ArrayList<>(listOfGames)) {
                if (game.getName().toLowerCase().equals(str)) {
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
