package blog.annoucement;

import blog.Controller;
import blog.annoucement.articles.Article;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Service;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostFreemarkerController implements Controller {
  private final Service service;
  private final PostService postService;
  private final FreeMarkerEngine freeMarkerEngine;

  public PostFreemarkerController(
          Service service,
          PostService postService,
          FreeMarkerEngine freeMarkerEngine
  ) {
    this.service = service;
    this.postService = postService;
    this.freeMarkerEngine = freeMarkerEngine;
  }

  @Override
  public void initializeEndpoints() {
    getAllPosts();
  }

  private void getAllPosts() {
    service.get(
            "/",
            (Request request, Response response) -> {
              response.type("text/html; charset=utf-8");
              List<Article> posts = postService.getAll();
              List<Map<String, String>> postMapList =
                      posts.stream()
                              .map(article -> Map.of(
                                      "header", article.getHeader(),
                                      "tags", article.getTags().toString(),
                                      "comments_count", article.getComments().size() + "",
                                      "id", article.getId().getId().toString()))
                              .toList();

              Map<String, Object> model = new HashMap<>();
              model.put("posts", postMapList);
              return freeMarkerEngine.render(new ModelAndView(model, "index.ftl"));
            }
    );
  }
}