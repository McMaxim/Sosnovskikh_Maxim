package org.example;

import blog.Application;
import blog.TemplateFactory;
import blog.annoucement.PostController;
import blog.annoucement.PostFreemarkerController;
import blog.annoucement.PostService;
import blog.annoucement.articles.InMemoryArticlesRepository;
import blog.annoucement.comments.InMemoryCommentsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Service;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    System.out.println("Запуск приложения...");
    Service service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();
    PostService postService = new PostService(
            new InMemoryArticlesRepository(),
            new InMemoryCommentsRepository()
    );
    Application application = new Application(
            List.of(
                    new PostController(
                            service,
                            postService,
                            objectMapper
                    ),
                    new PostFreemarkerController(
                            service,
                            postService,
                            TemplateFactory.freeMarkerEngine()
                    )
            )
    );
    application.start();
    System.out.println("Приложение успешно запущено и готово к работе!");
  }
}
