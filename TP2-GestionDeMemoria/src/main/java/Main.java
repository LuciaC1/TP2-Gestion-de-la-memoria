public class Main {
    public static void main(String[] args) {
        // Secuencia de ejemplo
        int[] exampleSeq = {
                1, 2, 3, 1, 3, 2, 1, 6, 4,
                1, 4, 8, 3, 6, 7, 5, 1, 2,
                4, 2, 1, 3, 1
        };
        int exampleFrames = 4;

        System.out.println("=== Simulacion Secuencia Ejemplo (4 marcos) ===");
        System.out.printf("FIFO     : %d fallos%n",
                PageReplacementSimulator.simulateFIFO(exampleSeq, exampleFrames));
        System.out.printf("LRU      : %d fallos%n",
                PageReplacementSimulator.simulateLRU(exampleSeq, exampleFrames));
        System.out.printf("optimo   : %d fallos%n",
                PageReplacementSimulator.simulateOptimal(exampleSeq, exampleFrames));
        System.out.printf("clock   : %d fallos%n",
                PageReplacementSimulator.simulateClock(exampleSeq, exampleFrames));

        // Otra secuencia de prueba
        int[] seq2 = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        System.out.println("=== Simulacion Secuencia 2 ===");
        System.out.printf("FIFO (3 marcos): %d fallos%n",
                PageReplacementSimulator.simulateFIFO(seq2, 3));
        System.out.printf("FIFO (4 marcos): %d fallos%n",
                PageReplacementSimulator.simulateFIFO(seq2, 4));
        System.out.println();

    }
}
