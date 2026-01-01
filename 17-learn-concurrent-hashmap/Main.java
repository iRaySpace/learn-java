import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // final var map = new HashMap<String, Object>();
        // map.put("counter", 0);

        final var map = new ConcurrentHashMap<String, Object>();
        map.put("counter", 0);

        System.out.println("map: " + map);

        final var executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                map.compute("counter", (key, value) -> (int) value + 1);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);

        System.out.println("updated map: " + map);
    }

}
