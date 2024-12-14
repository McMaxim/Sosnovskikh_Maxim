package blog.annoucement.comments;

import blog.annoucement.exceptions.CommentIdDublicationException;
import blog.annoucement.exceptions.CommentNotFoundException;

import java.util.List;

public interface CommentsRepository {
  CommentId generateId();

  List<Comment> getAll();

  Comment findById(CommentId id) throws CommentNotFoundException;

  void add(Comment comment) throws CommentIdDublicationException;

  void update(Comment comment) throws CommentNotFoundException;

  void delete(CommentId comment) throws CommentNotFoundException;
}