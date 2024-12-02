package blog.annoucement.articles;

import java.util.Objects;

public class ArticleId {
  private final Long id;

  public ArticleId(long id) {
    if (id < 0) {
      throw new IllegalArgumentException("ID не может быть отрицательным");
    }
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ArticleId articleId)) return false;
    return Objects.equals(id, articleId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "ArticleId{" +
            "id=" + id +
            '}';
  }
}
