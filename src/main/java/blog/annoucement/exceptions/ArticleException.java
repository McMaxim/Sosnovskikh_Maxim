package blog.annoucement.exceptions;

public class ArticleException extends Exception {
  public ArticleException(String message, Throwable e) {
    super(message, e);
  }
}