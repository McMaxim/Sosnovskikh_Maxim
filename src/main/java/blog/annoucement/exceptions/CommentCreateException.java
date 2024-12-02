package blog.annoucement.exceptions;

public class CommentCreateException extends CommentException {
  public CommentCreateException(String message, Throwable e) {
    super(message, e);
  }
}