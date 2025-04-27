import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Simulador de reemplazo de paginas. Cada metodo recibe el flujo de paginas
 * y la cantidad de marcos. Retorna el numero de fallos de pagina.
 */
public class PageReplacementSimulator {
    /**
     * Simulacion FIFO (First-In First-Out).
     *
     * @param pages flujo de paginas
     * @param frameCount numero de marcos de pagina disponibles
     * @return cantidad de fallos de pagina (solo al reemplazar)
     */
    public static int simulateFIFO(int[] pages, int frameCount) {
        int errors = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int page: pages){
            if (queue.contains(page)){
                continue;
            }
            if (queue.size() < frameCount){
                queue.offer(page);
            }else{
                queue.poll();
                queue.offer(page);
                errors = errors + 1;
            }
        }
        return errors;
    }

    /**
     * Simulacion LRU (Least Recently Used).
     *
     * @param pages flujo de paginas
     * @param frameCount numero de marcos de pagina disponibles
     * @return cantidad de fallos de pagina
     */
    public static int simulateLRU(int[] pages, int frameCount) { //falta arreglar
        int errors = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int page: pages){
            if (queue.size() < frameCount){
                queue.offer(page);
            }else {
                if (queue.remove(page)) {
                    queue.offer(page);
                } else {
                    queue.poll();
                    queue.offer(page);
                    errors = errors + 1;
                }
            }
        }
        return errors;
    }

    /**
     * Simulacion optima (Optimal Replacement).
     *
     * @param pages flujo de paginas
     * @param frameCount numero de marcos de pagina disponibles
     * @return cantidad de fallos de pagina
     */
    public static int simulateOptimal(int[] pages, int frameCount) {
        throw new UnsupportedOperationException("Optimal algorithm not implemented yet");
    }

    /**
     * Simulacion Clock (Second Chance).
     *
     * @param pages flujo de paginas
     * @param frameCount numero de marcos de pagina disponibles
     * @return cantidad de fallos de pagina
     */
    public static int simulateClock(int[] pages, int frameCount) {
        throw new UnsupportedOperationException("Clock algorithm not implemented yet");
    }
}
