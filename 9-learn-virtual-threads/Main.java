import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {

    static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    void main() throws Exception {
        final List<String> urls = List.of(
                "https://jsonplaceholder.typicode.com/posts/1",
                "https://jsonplaceholder.typicode.com/posts/2",
                "https://jsonplaceholder.typicode.com/posts/3",
                "https://jsonplaceholder.typicode.com/posts/4",
                "https://jsonplaceholder.typicode.com/posts/5");
        
        // // Simple for-loop
        // Instant start = Instant.now();
        // for (String url : urls) {
        //     final Optional<String> response = fetchData(url);
        //     if (response.isEmpty()) {
        //         System.out.println("No response from the server: could be an error");
        //         continue;
        //     }
        //     System.out.println("Response: " + response.get());
        // }
        // Duration duration = Duration.between(start, Instant.now());
        // System.out.println("Blocking request time: " + duration.toMillis() + "ms");

        // For loop with start virtual thread
        // Apparently, this has to wait because of the .join()
        // Instant start = Instant.now();
        // for (String url : urls) {
        //     Thread.startVirtualThread(() -> {
        //         final Optional<String> response = fetchData(url);
        //         if (response.isEmpty()) {
        //             System.out.println("No response from the server: could be an error");
        //         }
        //         System.out.println("Response: " + response.get());
        //     }).join();
        // }
        // Duration duration = Duration.between(start, Instant.now());
        // System.out.println("Virtual thread request time: " + duration.toMillis() + "ms");

        Instant start = Instant.now();
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String url : urls) {
                executorService.submit(() -> {
                    final Optional<String> response = fetchData(url);
                    if (response.isEmpty()) {
                        System.out.println("No response from the server: could be an error");
                    }
                    System.out.println("Response: " + response.get());
                });
            }
        }
        Duration duration = Duration.between(start, Instant.now());
        System.out.println("VirtualThreadPerTaskExecutor request time: " + duration.toMillis() + "ms");
    }

    Optional<String> fetchData(String url) {
        final HttpRequest request = HttpRequest.newBuilder().GET()
            .uri(URI.create(url)).build();
        try {
            final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(response.body());
        } catch (Exception ex) {
            return Optional.of(null);
        }
    }

}