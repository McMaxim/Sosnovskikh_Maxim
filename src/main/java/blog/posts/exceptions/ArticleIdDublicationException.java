package blog.posts.exceptions;

public class ArticleIdDublicationException extends Exception {
  public ArticleIdDublicationException(String message) {
    super(message);
  }
}