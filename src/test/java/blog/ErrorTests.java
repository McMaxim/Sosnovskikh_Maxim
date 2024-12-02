package blog;

import blog.annoucement.PostController;
import blog.annoucement.PostService;
import blog.annoucement.articles.InMemoryArticlesRepository;
import blog.annoucement.comments.InMemoryCommentsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorTests {
  private Service service;

  @BeforeEach
  void beforeEach() {
    service = Service.ignite();
  }

  @AfterEach
  void afterEach() {
    service.stop();
  }

  @Test
  void test404Errors() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    Application application = new Application(
            List.of(
                    new PostController(
                            service,
                            new PostService(
                                    new InMemoryArticlesRepository(),
                                    new InMemoryCommentsRepository()
                            ),
                            objectMapper
                    )
            )
    );

    application.start();
    service.awaitInitialization();

    HttpClient client = HttpClient.newHttpClient();

    // Запросы на несуществующие ресурсы
    assertEquals(404, client.send(
            HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:%d/api/articles/13".formatted(service.port()))).build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    ).statusCode());

    assertEquals(404, client.send(
            HttpRequest.newBuilder().DELETE().uri(URI.create("http://localhost:%d/api/comments/13".formatted(service.port()))).build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    ).statusCode());

    assertEquals(404, client.send(
            HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString("""
                { "header": "updated article", "tags": ["newest", "the best"] }
                """))
                    .uri(URI.create("http://localhost:%d/api/articles/13".formatted(service.port())))
                    .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    ).statusCode());
  }

  @Test
  void testInvalidCommentPost() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    Application application = new Application(
            List.of(
                    new PostController(
                            service,
                            new PostService(
                                    new InMemoryArticlesRepository(),
                                    new InMemoryCommentsRepository()
                            ),
                            objectMapper
                    )
            )
    );

    application.start();
    service.awaitInitialization();

    HttpClient client = HttpClient.newHttpClient();

    HttpResponse<String> response = client.send(
            HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString("""
                    { "article": 13, "text": "Hi, how are u?" }
                    """))
                    .uri(URI.create("http://localhost:%d/api/comments".formatted(service.port())))
                    .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );

    assertEquals(400, response.statusCode());
  }
}
