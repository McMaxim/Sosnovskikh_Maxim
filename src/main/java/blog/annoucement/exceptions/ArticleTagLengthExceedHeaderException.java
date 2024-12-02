package blog.annoucement.exceptions;

public class ArticleTagLengthExceedHeaderException extends Exception {
  public ArticleTagLengthExceedHeaderException(String message) {
    super(message);
  }
}