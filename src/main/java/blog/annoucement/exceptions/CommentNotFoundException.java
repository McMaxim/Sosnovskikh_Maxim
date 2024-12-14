package blog.annoucement.exceptions;

public class CommentNotFoundException extends Exception {
  public CommentNotFoundException(String message) {
    super(message);
  }
}