package blog.annoucement.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ArticleCreateResponse(Long id, String message) {
  @JsonCreator
  public ArticleCreateResponse(@JsonProperty("id") Long id, @JsonProperty("message") String message) {
    this.message = message;
    this.id = id;
  }
}