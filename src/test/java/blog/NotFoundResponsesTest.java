package blog;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundResponsesTest extends ApplicationSetupTest {

  @Test
  void testNotFoundResponses() throws Exception {
    HttpResponse<String> response;

    response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("http://localhost:%d/api/articles/13".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(404, response.statusCode());

    response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("http://localhost:%d/api/comments/13".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(404, response.statusCode());
  }
}
