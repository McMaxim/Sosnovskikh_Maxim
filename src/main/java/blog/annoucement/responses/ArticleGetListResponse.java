package blog.annoucement.responses;

import blog.annoucement.articles.Article;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ArticleGetListResponse(List<Article> articles) {
  @JsonCreator
  public ArticleGetListResponse(@JsonProperty("articles") List<Article> articles) {
    this.articles = articles;
  }
}