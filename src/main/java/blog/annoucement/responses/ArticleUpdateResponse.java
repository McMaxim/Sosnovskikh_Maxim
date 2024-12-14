package blog.annoucement.responses;

import com.fasterxml.jackson.annotation.JsonCreator;

public record ArticleUpdateResponse() {
  @JsonCreator
  public ArticleUpdateResponse() {}
}