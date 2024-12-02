package blog;

import blog.annoucement.responses.ArticleCreateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleUpdateAndDeleteTest extends ApplicationSetupTest {

  @Test
  void testUpdateAndDeleteArticle() throws Exception {
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

    ArticleCreateResponse articleCreateResponse = objectMapper.readValue(
            response.body(),
            ArticleCreateResponse.class
    );
    long newArticleId = articleCreateResponse.id();

    response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .PUT(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                { "header": "updated article", "tags": ["newest", "best"] }
                                                """
                                    )
                            )
                            .uri(URI.create("http://localhost:%d/api/articles/%d".formatted(service.port(), newArticleId)))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(204, response.statusCode());

    response = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .DELETE()
                            .uri(URI.create("http://localhost:%d/api/articles/%d".formatted(service.port(), newArticleId)))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(200, response.statusCode());
  }
}
