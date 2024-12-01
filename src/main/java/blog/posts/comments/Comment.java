package blog.posts.comments;

import blog.posts.articles.ArticleId;

import java.util.Objects;

public class Comment {
  private final CommentId id;
  private final ArticleId article;

  String text;

  public Comment(CommentId id, ArticleId article, String text) {
    this.id = id;
    this.article = article;
    this.text = text;
  }

  public Comment updateText(String text) {
    return new Comment(this.id, this.article, text);
  }

  public ArticleId getArticle() {
    return this.article;
  }

  public CommentId getId() {
    return this.id;
  }

  public String getText() {
    return this.text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Comment comment = (Comment) o;
    return Objects.equals(id, comment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}