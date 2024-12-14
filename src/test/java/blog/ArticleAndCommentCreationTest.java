package blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleAndCommentCreationTest extends ApplicationSetupTest {

  @Test
  void testCreateArticleAndComment() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    HttpResponse<String> response;

    response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                { "header": "test", "tags": ["test"] }
                                                """
                                    )
                            )
                            .uri(URI.create("http://localhost:%d/api/articles".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(201, response.statusCode());

    long newArticleId = objectMapper.readTree(response.body()).get("id").asLong();

    response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                            { "article": %d, "text": "Hi, how are u?" }
                                            """.formatted(newArticleId)
                                    )
                            )
                            .uri(URI.create("http://localhost:%d/api/comments".formatted(service.port())))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(201, response.statusCode());

    response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("http://localhost:%d/api/articles/%d".formatted(service.port(), newArticleId)))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(200, response.statusCode());
    assertEquals(1, objectMapper.readTree(response.body()).get("comments").size());
  }
}
