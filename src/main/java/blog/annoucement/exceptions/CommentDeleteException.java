package blog.annoucement.exceptions;

public class CommentDeleteException extends CommentException {
  public CommentDeleteException(String message, Throwable e) {
    super(message, e);
  }
}