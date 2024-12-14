package blog;

import blog.annoucement.PostController;
import blog.annoucement.PostService;
import blog.annoucement.articles.InMemoryArticlesRepository;
import blog.annoucement.comments.InMemoryCommentsRepository;
import blog.annoucement.responses.ArticleCreateResponse;
import blog.annoucement.responses.ArticleGetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticleTests {
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
  void testArticleLifecycle() throws Exception {
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

    // Создание статьи
    HttpResponse<String> response = client.send(
            HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString("""
                    { "header": "test", "tags": ["test"] }
                    """))
                    .uri(URI.create("http://localhost:%d/api/articles".formatted(service.port())))
                    .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );

    assertEquals(201, response.statusCode());

    ArticleCreateResponse articleCreateResponse = objectMapper.readValue(response.body(), ArticleCreateResponse.class);
    long newArticleId = articleCreateResponse.id();

    // Проверка созданной статьи
    response = client.send(
            HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:%d/api/articles/%d".formatted(service.port(), newArticleId)))
                    .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );

    assertEquals(200, response.statusCode());

    ArticleGetResponse articleGetResponse = objectMapper.readValue(response.body(), ArticleGetResponse.class);
    assertEquals("test", articleGetResponse.header());
    assertEquals(new HashSet<>(List.of("test")), articleGetResponse.tags());
  }
}
