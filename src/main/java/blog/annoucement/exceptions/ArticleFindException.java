package blog.annoucement.exceptions;

public class ArticleFindException extends ArticleException {
  public ArticleFindException(String message, Throwable e) {
    super(message, e);
  }
}