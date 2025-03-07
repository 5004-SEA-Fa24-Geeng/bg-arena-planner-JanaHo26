package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {

    @Test
    public void testFilterString() {
        assertTrue(Filters.filterString("Go", Operations.EQUALS, "Go"));
        assertTrue(Filters.filterString("Go", Operations.EQUALS, "go"));
        assertFalse(Filters.filterString("Go", Operations.EQUALS, "Chess"));
        assertFalse(Filters.filterString("Go", Operations.NOT_EQUALS, "Go"));
        assertTrue(Filters.filterString("Go", Operations.NOT_EQUALS, "Chess"));

        assertTrue(Filters.filterString("Go Fish", Operations.CONTAINS, "Fish"));
        assertTrue(Filters.filterString("Go Fish", Operations.CONTAINS, "fish"));
        assertFalse(Filters.filterString("Go Fish", Operations.CONTAINS, "chess"));
    }

    @Test
    public void testFilterInteger() {
        assertTrue(Filters.filterInt(5, Operations.EQUALS, "5"));
        assertFalse(Filters.filterInt(5, Operations.EQUALS, "6"));

        assertTrue(Filters.filterInt(5, Operations.NOT_EQUALS, "6"));
        assertFalse(Filters.filterInt(5, Operations.NOT_EQUALS, "5"));

        assertTrue(Filters.filterInt(5, Operations.GREATER_THAN, "4"));
        assertFalse(Filters.filterInt(5, Operations.GREATER_THAN, "5"));
        assertFalse(Filters.filterInt(5, Operations.GREATER_THAN, "6"));

        assertFalse(Filters.filterInt(5, Operations.LESS_THAN, "4"));
        assertFalse(Filters.filterInt(5, Operations.LESS_THAN, "5"));
        assertTrue(Filters.filterInt(5, Operations.LESS_THAN, "6"));

        assertTrue(Filters.filterInt(5, Operations.GREATER_THAN_EQUALS, "5"));
        assertFalse(Filters.filterInt(5, Operations.GREATER_THAN_EQUALS, "6"));
        assertFalse(Filters.filterInt(5, Operations.LESS_THAN_EQUALS, "4"));

        assertTrue(Filters.filterInt(5, Operations.LESS_THAN_EQUALS, "5"));
        assertFalse(Filters.filterInt(5, Operations.GREATER_THAN_EQUALS, "6"));
        assertTrue(Filters.filterInt(5, Operations.GREATER_THAN_EQUALS, "4"));
    }


    @Test
    public void testFilterDouble() {
        assertTrue(Filters.filterDouble(5.5, Operations.EQUALS, "5.5"));
        assertFalse(Filters.filterDouble(5.5, Operations.EQUALS, "5.6"));

        assertTrue(Filters.filterDouble(5.5, Operations.NOT_EQUALS, "5.6"));
        assertFalse(Filters.filterDouble(5.5, Operations.NOT_EQUALS, "5.5"));

        assertTrue(Filters.filterDouble(5.5, Operations.GREATER_THAN, "5.4"));
        assertFalse(Filters.filterDouble(5.5, Operations.GREATER_THAN, "5.5"));
        assertFalse(Filters.filterDouble(5.5, Operations.GREATER_THAN, "5.6"));

        assertTrue(Filters.filterDouble(5.5, Operations.LESS_THAN, "5.6"));
        assertFalse(Filters.filterDouble(5.5, Operations.LESS_THAN, "5.5"));
        assertFalse(Filters.filterDouble(5.5, Operations.LESS_THAN, "5.4"));

        assertTrue(Filters.filterDouble(5.5, Operations.GREATER_THAN_EQUALS, "5.5"));
        assertTrue(Filters.filterDouble(5.5, Operations.GREATER_THAN_EQUALS, "5.4"));
        assertFalse(Filters.filterDouble(5.5, Operations.GREATER_THAN_EQUALS, "5.6"));

        assertTrue(Filters.filterDouble(5.5, Operations.LESS_THAN_EQUALS, "5.5"));
        assertTrue(Filters.filterDouble(5.5, Operations.LESS_THAN_EQUALS, "5.6"));
        assertFalse(Filters.filterDouble(5.5, Operations.LESS_THAN_EQUALS, "5.4"));
    }

    @Test
    public void testFilter() {
        BoardGame game = new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000);

        assertTrue(Filters.filter(game, GameData.NAME, Operations.EQUALS, "Go"));
        assertTrue(Filters.filter(game, GameData.NAME, Operations.CONTAINS, "G"));
        assertFalse(Filters.filter(game, GameData.NAME, Operations.EQUALS, "Chess"));

        assertTrue(Filters.filter(game, GameData.MIN_PLAYERS, Operations.EQUALS, "2"));
        assertTrue(Filters.filter(game, GameData.MAX_PLAYERS, Operations.EQUALS, "5"));
        assertTrue(Filters.filter(game, GameData.MIN_PLAYERS, Operations.LESS_THAN, "3"));
        assertTrue(Filters.filter(game, GameData.MAX_PLAYERS, Operations.GREATER_THAN, "4"));

        assertTrue(Filters.filter(game, GameData.MIN_TIME, Operations.EQUALS, "30"));
        assertTrue(Filters.filter(game, GameData.MAX_TIME, Operations.EQUALS, "30"));

        assertTrue(Filters.filter(game, GameData.DIFFICULTY, Operations.EQUALS, "8.0"));

        assertTrue(Filters.filter(game, GameData.YEAR, Operations.EQUALS, "2000"));
    }


}
