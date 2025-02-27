package student;


import java.util.Set;
import java.util.stream.Stream;


public class Planner implements IPlanner {
    private final Set<BoardGame> game;
    private Stream<BoardGame> currentFilter;

    public Planner(Set<BoardGame> games) {
        this.game = games;
        reset();
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        return filter(filter, GameData.NAME, true);
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        return filter(filter, sortOn, true);
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        if (filter.isEmpty()) {
            return sortStream(currentFilter, sortOn, ascending);
        }






        Stream<BoardGame> filteredstream = filterSingle(filter, games.stream());
        return filteredstream;
    }

    private Stream<BoardGame> filterSingle(String filter, Stream<BoardGames> filteredGames) {
        filter = filter.replaceAll(" ", "");

        String[] filters = filter.split(operator.getOperator());
        if (parts.length != 2) {
            return filteredGames;
        }
        GameData column;
        try {
            column = GameData.fromString(parts[0]);
        } catch (IllegalArgumentException e) {
            return filteredGames;
        }
        // more work here to filter the games
        //we found creating a String filter and a number filter to be useful

        String value;
        try {
            value = parts[1].trim();
        } catch (IllegalArgumentException e) {
            return filteredGames;
        }
        System.out.print("Operator is :" + operator);
        System.out.print("GAmeData is :" + column);
        System.out.print("Value is :" + value);
        //Filters.filter(board game, game data, operator, String value)
        //Stream<BoardGame> filteredGameList
        List<BoardGame> filteredGameList = filteredGames.filter(game->
                Filters.filter(game, column, operator, value)).toList();
        return filteredGamesList.stream();
    }

//    public Planner(Set<BoardGame> games) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented constructor 'Planner'");
//    }




    @Override
    public Stream<BoardGame> filter(String filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }

    @Overridpackage student;


import java.util.Set;
import java.util.stream.Stream;


    public class Planner implements IPlanner {
        Set<BoardGame> Game;

        public Planner(Set<BoardGame> games) {
            this.games = games;
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented constructor 'Planner'");
        }

        @Override
        public Stream<BoardGame> filter(String filter) {
            //return Stream<BoardGame>
            //"name == Go"

            //think of the case where filter only has one filter
            Stream<BoardGame> filteredstream = filterSingle(filter, games.stream());
            return filteredstream;
        }

        private Stream<BoardGames> filterSingle(String filter, Stream<BoardGames> filteredGames) {
            filter = filter.replaceAll(" ", "");

            String[] filters = filter.split(operator.getOperator());
            if (parts.length != 2) {
                return filteredGames;
            }
            GameData column;
            try {
                column = GameData.fromString(parts[0]);
            } catch (IllegalArgumentException e) {
                return filteredGames;
            }
            // more work here to filter the games
            //we found creating a String filter and a number filter to be useful

            String value;
            try {
                value = parts[1].trim();
            } catch (IllegalArgumentException e) {
                return filteredGames;
            }
            System.out.print("Operator is :" + operator);
            System.out.print("GAmeData is :" + column);
            System.out.print("Value is :" + value);
            //Filters.filter(board game, game data, operator, String value)
            //Stream<BoardGame> filteredGameList
            List<BoardGame> filteredGameList = filteredGames.filter(game->
                    Filters.filter(game, column, operator, value)).toList();
            return filteredGamesList.stream();
        }

//    public Planner(Set<BoardGame> games) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented constructor 'Planner'");
//    }




        @Override
        public Stream<BoardGame> filter(String filter) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'filter'");
        }

        @Override
        public Stream<BoardGame> filter(String filter, GameData sortOn) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'filter'");
        }

        @Override
        public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'filter'");
        }

        @Override
        public void reset() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'reset'");
        }


    }
    e
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }


}
