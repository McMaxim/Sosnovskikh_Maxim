package blog.annoucement.comments;

import blog.annoucement.exceptions.CommentIdDublicationException;
import blog.annoucement.exceptions.CommentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryCommentsRepository implements CommentsRepository {
  private final Map<CommentId, Comment> comments = new ConcurrentHashMap<>();
  private final AtomicLong nextId = new AtomicLong(0);

  @Override
  public CommentId generateId() {
    return new CommentId(nextId.getAndIncrement());
  }

  @Override
  public List<Comment> getAll() {
    return new ArrayList<>(comments.values());
  }

  @Override
  public Comment findById(CommentId id) throws CommentNotFoundException {
    if (!comments.containsKey(id)) {
      throw new CommentNotFoundException("Комментарий с ID " + id.getId() + " не найден.");
    }
    return comments.get(id);
  }

  @Override
  public synchronized void add(Comment comment) throws CommentIdDublicationException {
    if (comments.containsKey(comment.getId())) {
      throw new CommentIdDublicationException("Комментарий с таким ID уже существует.");
    }
    comments.put(comment.getId(), comment);
  }

  @Override
  public synchronized void update(Comment comment) throws CommentNotFoundException {
    if (!comments.containsKey(comment.getId())) {
      throw new CommentNotFoundException("Обновление невозможно: комментарий с таким ID не найден.");
    }
    comments.put(comment.getId(), comment);
  }

  @Override
  public synchronized void delete(CommentId comment) throws CommentNotFoundException {
    if (!comments.containsKey(comment)) {
      throw new CommentNotFoundException("Удаление невозможно: комментарий с таким ID не найден.");
    }
    comments.remove(comment);
  }

}