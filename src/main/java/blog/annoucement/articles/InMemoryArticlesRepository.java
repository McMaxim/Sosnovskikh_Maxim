package blog.annoucement.articles;

import blog.annoucement.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryArticlesRepository implements ArticlesRepository {
  static final long MAX_HEADER_LENGTH = 1024L;
  static final long MAX_TAGS_COUNT = 256L;
  static final long MAX_TAG_LENGTH = 256L;

  private final Map<ArticleId, Article> articles = new ConcurrentHashMap<>();
  private final AtomicLong nextId = new AtomicLong(0);

  private void checkArticleData(Article article)
          throws ArticleTagLengthExceedHeaderException, ArticleTagsCountExceedHeaderException, ArticleHeaderExceedHeaderException {
    if (article.getHeader().length() > MAX_HEADER_LENGTH) {
      throw new ArticleHeaderExceedHeaderException("Заголовок превышает допустимую длину.");
    }
    if (article.getTags().size() > MAX_TAGS_COUNT) {
      throw new ArticleTagsCountExceedHeaderException("Превышено максимальное количество тегов для статьи.");
    }
    for (var tag : article.getTags()) {
      if (tag.length() > MAX_TAG_LENGTH) {
        throw new ArticleTagLengthExceedHeaderException("Длина тега превышает допустимое значение.");
      }
    }
  }

  @Override
  public ArticleId generateId() {
    return new ArticleId(nextId.getAndIncrement());
  }

  @Override
  public List<Article> getAll() {
    return new ArrayList<>(articles.values());
  }

  @Override
  public Article findById(ArticleId id) throws ArticleNotFoundException {
    if (!articles.containsKey(id)) {
      throw new ArticleNotFoundException("Статья с ID " + id.getId() + " не найдена.");
    }
    return articles.get(id);
  }

  @Override
  public synchronized void add(Article article)
          throws ArticleIdDublicationException, ArticleHeaderExceedHeaderException, ArticleTagsCountExceedHeaderException, ArticleTagLengthExceedHeaderException {
    checkArticleData(article);
    if (articles.containsKey(article.getId())) {
      throw new ArticleIdDublicationException("Статья с таким ID уже существует.");
    }
    articles.put(article.getId(), article);
  }

  @Override
  public synchronized void update(Article article)
          throws ArticleNotFoundException, ArticleTagsCountExceedHeaderException, ArticleTagLengthExceedHeaderException, ArticleHeaderExceedHeaderException {
    checkArticleData(article);
    if (!articles.containsKey(article.getId())) {
      throw new ArticleNotFoundException("Невозможно обновить: статья с таким ID не найдена.");
    }
    articles.put(article.getId(), article);
  }

  @Override
  public synchronized void delete(ArticleId articleId) throws ArticleNotFoundException {
    if (!articles.containsKey(articleId)) {
      throw new ArticleNotFoundException("Удаление невозможно: статья с таким ID не найдена.");
    }
    articles.remove(articleId);
  }
}