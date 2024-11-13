package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {

    @Mock
    private BoardFactory boardFactory;

    @Mock
    private LevelFactory levelFactory;

    @Mock
    private Blinky blinky;

    private MapParser mapParser;

    @BeforeEach
    public void setUp() {
        mapParser = new MapParser(levelFactory, boardFactory);
    }

    /**
     * Test for the parseMap method (good map).
     */
    @Test
    public void testParseMapGood() {
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);

        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);

        List<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");

        mapParser.parseMap(map);

        // Verify that createGhost() is called exactly once
        Mockito.verify(levelFactory, Mockito.times(1)).createGhost();
    }

    /**
     * Test for the parseMap method (bad map).
     */
    @Test
    public void testParseMapWrong1() {
        PacmanConfigurationException thrown =
            Assertions.assertThrows(PacmanConfigurationException.class, () -> {
                assertNotNull(boardFactory);
                assertNotNull(levelFactory);
                ArrayList<String> map = new ArrayList<>();
                // Add inconsistent or invalid map rows
                map.add("############");
                map.add("#P    X   G#"); // Contains invalid character 'X'
                map.add("#######");      // Inconsistent row size
                mapParser.parseMap(map);
            });
        Assertions.assertEquals("Input text lines are not of equal width.", thrown.getMessage());
    }
}
