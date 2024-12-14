package blog;

import blog.annoucement.PostController;
import blog.annoucement.PostService;
import blog.annoucement.articles.InMemoryArticlesRepository;
import blog.annoucement.comments.InMemoryCommentsRepository;
import blog.annoucement.responses.CommentCreateResponse;
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

class CommentTests {
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
  void testCommentLifecycle() throws Exception {
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

    // Создание статьи для комментария
    HttpResponse<String> articleResponse = client.send(
            HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString("""
                    { "header": "test article", "tags": ["test"] }
                    """))
                    .uri(URI.create("http://localhost:%d/api/articles".formatted(service.port())))
                    .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );

    long articleId = new ObjectMapper().readValue(articleResponse.body(), blog.annoucement.responses.ArticleCreateResponse.class).id();

    // Создание комментария
    HttpResponse<String> commentResponse = client.send(
            HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString("""
                    { "article": %d, "text": "Hi, how are u?" }
                    """.formatted(articleId)))
                    .uri(URI.create("http://localhost:%d/api/comments".formatted(service.port())))
                    .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );

    assertEquals(201, commentResponse.statusCode());

    CommentCreateResponse commentCreateResponse = objectMapper.readValue(commentResponse.body(), CommentCreateResponse.class);
    long commentId = commentCreateResponse.id();

    // Удаление комментария
    HttpResponse<String> deleteResponse = client.send(
            HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create("http://localhost:%d/api/comments/%d".formatted(service.port(), commentId)))
                    .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );

    assertEquals(200, deleteResponse.statusCode());
  }
}
