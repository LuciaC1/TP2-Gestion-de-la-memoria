import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageReplaceentSimulatorTest {
    // Secuencia ejemplo y cantidad de marcos
    private static final int[] EXAMPLE_REF = {
            1, 2, 3, 1, 3, 2, 1, 6, 4,
            1, 4, 8, 3, 6, 7, 5, 1, 2,
            4, 2, 1, 3, 1
    };

    private static final int EXAMPLE_FRAMES = 4;

    @Test
    public void givenExampleSequence_FIFO() {
        assertEquals(
                11,
                PageReplacementSimulator.simulateFIFO(EXAMPLE_REF, EXAMPLE_FRAMES)
        );
    }

    @Test
    public void givenExampleSequence_LRU() {
        assertEquals(
                10,
                PageReplacementSimulator.simulateLRU(EXAMPLE_REF, EXAMPLE_FRAMES)
        );
    }

    @Test
    public void givenExampleSequence_Optimal() {
        assertEquals(
                6,
                PageReplacementSimulator.simulateOptimal(EXAMPLE_REF, EXAMPLE_FRAMES)
        );
    }


    @Test
    public void case1_NoReplacement_WhenFramesNotFull() {
        int[] seq = {1, 2, 3};
        int frames = 5; // mas marcos que referencias

        // Ningun algoritmo debe contar fallos si aun hay marcos libres
        assertEquals(0, PageReplacementSimulator.simulateFIFO(seq, frames));
        assertEquals(0, PageReplacementSimulator.simulateLRU(seq, frames));
        assertEquals(0, PageReplacementSimulator.simulateOptimal(seq, frames));
    }

    @Test
    public void case2_NoReplacement_RepeatingPattern() {
        int[] seq = {1, 2, 1, 2, 1, 2};
        int frames = 2;

        // Patron repetitivo no debe generar ningun reemplazo
        assertEquals(0, PageReplacementSimulator.simulateFIFO(seq, frames));
        assertEquals(0, PageReplacementSimulator.simulateLRU(seq, frames));
        assertEquals(0, PageReplacementSimulator.simulateOptimal(seq, frames));
    }

    @Test
    public void case3_Replacement_AllDistinctPages() {
        int[] seq = {1, 2, 3, 4, 5};
        int frames = 3;

        // Cada algoritmo deberia producir dos reemplazos
        assertEquals(2, PageReplacementSimulator.simulateFIFO(seq, frames));
        assertEquals(2, PageReplacementSimulator.simulateLRU(seq, frames));
        assertEquals(2, PageReplacementSimulator.simulateOptimal(seq, frames));
    }
}
