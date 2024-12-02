package blog.annoucement.exceptions;

public class ArticleUpdateException extends ArticleException {
  public ArticleUpdateException(String message, Throwable e) {
    super(message, e);
  }
}