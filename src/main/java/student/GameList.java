package student;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class GameList implements IGameList {
    Set<> listOfGames;

    /**
     * Constructor for the GameList.
     */
    public GameList() {
        ListOfGames = new HashSet<>();
        //throw new UnsupportedOperationException("Unimplemented constructor 'GameList'");
    }

    @Override
    public List<String> getGameNames() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getGameNames'");
        List<String> listVersionOfGames = List.copyOf(ListOfGames);
        return ListVersionofGames
    }


    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'count'");

        return ListofGame
    }

    @Override
    public void saveGame(String filename) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGame'");
    }

    @Override
    public void addToList(String str, Stream<BoardGame> filtered) throws IllegalArgumentException {

        //ListOfGames HashSet, str operation, Stream<BoardGame> filtered
        List<BoardGame> filteredList = filtered.toList();
        BoardGame toAdd = filteredList.get(Integer.parseInt(str));
        ListOfGames.add(toAdd.getName());


        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToList'");
    }

    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromList'");
    }


}
