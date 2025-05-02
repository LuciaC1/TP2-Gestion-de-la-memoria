import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

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
                queue.add(page);
            }else{
                queue.poll();
                queue.add(page);
                errors++;
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
    public static int simulateLRU(int[] pages, int frameCount) {
        int errores = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int page : pages) {
            if (queue.contains(page)) {
                queue.remove(page);
                queue.add(page);
            } else {
                if (queue.size() < frameCount) {
                    queue.add(page);
                } else {
                    queue.poll();
                    queue.add(page);
                    errores++;
                }
            }
        }
        return errores;
    }

    /**
     * Simulacion optima (Optimal Replacement).
     *
     * @param pages flujo de paginas
     * @param frameCount numero de marcos de pagina disponibles
     * @return cantidad de fallos de pagina
     */
    public static int simulateOptimal(int[] pages, int frameCount) {
        int errores = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int page : pages) {
            if (queue.contains(page)) {
                continue;
            }
            if (queue.size() < frameCount) {
                queue.offer(page);
            } else {
                int contador = 0;
                Deque<Integer> deque = new LinkedList<>();
                for (int page2 : pages) {
                    if (contador == 0) {
                        page2 = page;
                        contador++;
                    } else {
                        if (queue.contains(page2)) {
                            if (deque.size() < frameCount && !deque.contains(page2)) {
                                deque.add(page2);
                            }
                        }
                    }
                }
                int ultimo = deque.getLast();
                queue.remove(ultimo);
                queue.add(page);
                errores++;
            }
        }
        return errores;
    }

    /**
     * Simulacion Clock (Second Chance).
     *
     * @param pages flujo de paginas
     * @param frameCount numero de marcos de pagina disponibles
     * @return cantidad de fallos de pagina
     */
    public static int simulateClock(int[] pages, int frameCount) {
        int errors = 0; boolean verif = true;
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        for (int page: pages){
            if (queue.contains(page)){
                if (!queue2.contains(page) && queue2.size() < frameCount) {
                    queue2.add(page);
                }
                continue;
            }
            if (queue.size() < frameCount){
                queue.add(page);
            } else {
                int aux = 0;
                for (int i = 0; i < frameCount; i++) {
                    if (!queue2.contains(queue.peek())) {
                        queue.poll();
                        queue.add(page);
                        errors++;
                        break;
                    } else {
                        queue2.remove(queue.peek());
                        aux = queue.peek();
                        queue.poll();
                        queue.add(aux);
                    }
                }
                if (!queue.contains(page)) {
                    queue.poll();
                    queue.add(page);
                    errors++;
                }
            }
        }
        return errors;
    }
}
