package blog.annoucement.exceptions;


public class ArticleCreateException extends ArticleException {
  public ArticleCreateException(String message, Throwable e) {
    super(message, e);
  }
}