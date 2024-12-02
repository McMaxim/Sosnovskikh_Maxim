package blog.annoucement.articles;

import blog.annoucement.comments.Comment;
import blog.annoucement.exceptions.CommentNotFoundException;

import java.util.*;

public class Article {
  private final ArticleId id;

  final String header;
  final Set<String> tags;
  final List<Comment> comments;

  public Article(ArticleId id, String header, Set<String> tags, List<Comment> comments) {
    this.id = id;
    this.header = header;
    this.tags = tags;
    this.comments = comments;
  }

  public ArticleId getId() {
    return this.id;
  }

  public String getHeader() {
    return this.header;
  }

  public Set<String> getTags() {
    return new HashSet<>(this.tags);
  }

  public List<Comment> getComments() {
    return new ArrayList<>(this.comments);
  }

  public Article addComment(Comment comment) {
    List<Comment> newComments = new ArrayList<>(this.comments);
    newComments.add(comment);
    return new Article(getId(), getHeader(), getTags(), newComments);
  }

  public Article removeComment(Comment comment) throws CommentNotFoundException {
    if (!this.comments.contains(comment)) {
      throw new CommentNotFoundException("Комментарий не найден");
    }
    List<Comment> newComments = new ArrayList<>(this.comments);
    newComments.remove(comment);
    return new Article(getId(), getHeader(), getTags(), newComments);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return Objects.equals(id, article.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}