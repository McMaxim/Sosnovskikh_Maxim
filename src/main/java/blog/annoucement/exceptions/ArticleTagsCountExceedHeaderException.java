package blog.annoucement.exceptions;

public class ArticleTagsCountExceedHeaderException extends Exception {
  public ArticleTagsCountExceedHeaderException(String message) {
    super(message);
  }
}