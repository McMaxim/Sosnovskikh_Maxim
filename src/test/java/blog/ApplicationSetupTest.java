package blog;

import blog.annoucement.PostController;
import blog.annoucement.PostService;
import blog.annoucement.articles.InMemoryArticlesRepository;
import blog.annoucement.comments.InMemoryCommentsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import spark.Service;

import java.util.List;

public abstract class ApplicationSetupTest {
  protected Service service;
  protected Application application;

  @BeforeEach
  void setUp() {
    service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();
    application = new Application(
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
  }

  @AfterEach
  void tearDown() {
    service.stop();
  }
}
