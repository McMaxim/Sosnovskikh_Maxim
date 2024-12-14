package blog.annoucement;

import blog.annoucement.exceptions.*;
import blog.annoucement.articles.Article;
import blog.annoucement.articles.ArticleId;
import blog.annoucement.articles.ArticlesRepository;
import blog.annoucement.comments.Comment;
import blog.annoucement.comments.CommentId;
import blog.annoucement.comments.CommentsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PostService {
  private final ArticlesRepository articlesRepository;
  private final CommentsRepository commentsRepository;

  public PostService(ArticlesRepository articlesRepository, CommentsRepository commentsRepository) {
    this.articlesRepository = articlesRepository;
    this.commentsRepository = commentsRepository;
  }

  public List<Article> getAll() {
    return this.articlesRepository.getAll();
  }

  public Article findArticleById(long id) throws ArticleFindException {
    try {
      return this.articlesRepository.findById(new ArticleId(id));
    } catch (ArticleNotFoundException e) {
      throw new ArticleFindException("Статья с ID " + id + " не найдена.", e);
    }
  }

  public void updateArticle(long id, String header, Set<String> tags, List<Comment> comments) throws ArticleUpdateException {
    try {
      ArticleId articleId = new ArticleId(id);
      Article articleToUpdate = this.articlesRepository.findById(articleId);

      if (header == null) {
        header = articleToUpdate.getHeader();
      }
      if (tags == null) {
        tags = articleToUpdate.getTags();
      }
      if (comments == null) {
        comments = articleToUpdate.getComments();
      }

      Article updatedArticle = new Article(
              articleId,
              header,
              tags,
              comments
      );
      this.articlesRepository.update(updatedArticle);
    } catch (ArticleNotFoundException e) {
      throw new ArticleUpdateException("Не удалось обновить статью: статья с ID " + id + " не найдена.", e);
    } catch (ArticleTagsCountExceedHeaderException | ArticleTagLengthExceedHeaderException | ArticleHeaderExceedHeaderException e) {
      throw new ArticleUpdateException("Превышены ограничения для данных статьи.", e);
    }
  }

  public long createArticle(String header, Set<String> tags) throws ArticleCreateException {
    if (header == null || tags == null) {
      throw new ArticleCreateException("Создание статьи невозможно: один или несколько параметров отсутствуют.", new Exception());
    }

    Article newArticle = new Article(
            articlesRepository.generateId(),
            header,
            tags,
            new ArrayList<>()
    );

    try {
      this.articlesRepository.add(newArticle);
      return newArticle.getId().getId();
    } catch (ArticleIdDublicationException e) {
      throw new ArticleCreateException("Создание статьи невозможно: статья с таким ID уже существует.", e);
    } catch (ArticleTagsCountExceedHeaderException | ArticleTagLengthExceedHeaderException | ArticleHeaderExceedHeaderException e) {
      throw new ArticleCreateException("Превышены ограничения для данных статьи.", e);
    }
  }

  public void deleteArticle(long id) throws ArticleDeleteException {
    try {
      this.articlesRepository.delete(new ArticleId(id));
    } catch (ArticleNotFoundException e) {
      throw new ArticleDeleteException("Удаление невозможно: статья с ID " + id + " не найдена.", e);
    }
  }

  public long createComment(long articleId, String text) throws CommentCreateException {
    if (text == null) {
      throw new CommentCreateException("Создание комментария невозможно: текст комментария отсутствует.", new Exception());
    }

    Comment newComment = new Comment(
            commentsRepository.generateId(),
            new ArticleId(articleId),
            text
    );

    try {
      this.commentsRepository.add(newComment);
      Article articleWithNewComment = findArticleById(articleId);
      Article updatedArticle = articleWithNewComment.addComment(newComment);
      articlesRepository.update(updatedArticle);
    } catch (CommentIdDublicationException e) {
      throw new CommentCreateException("Создание комментария невозможно: комментарий с таким ID уже существует.", e);
    } catch (ArticleFindException | ArticleNotFoundException e) {
      throw new CommentCreateException("Создание комментария невозможно: статья с ID " + articleId + " не найдена.", e);
    } catch (ArticleTagsCountExceedHeaderException | ArticleTagLengthExceedHeaderException | ArticleHeaderExceedHeaderException e) {
      throw new CommentCreateException("Превышены ограничения для данных статьи.", e);
    }
    return newComment.getId().getId();
  }

  public void deleteComment(long id) throws CommentDeleteException {
    try {
      CommentId commentId = new CommentId(id);
      Comment commentToDelete = this.commentsRepository.findById(commentId);
      commentsRepository.delete(commentId);
      ArticleId articleId = commentToDelete.getArticle();
      Article articleWithDeletedComment = this.articlesRepository.findById(articleId);
      Article updatedArticle = articleWithDeletedComment.removeComment(commentToDelete);
      articlesRepository.update(updatedArticle);
    } catch (CommentNotFoundException e) {
      throw new CommentDeleteException("Удаление невозможно: комментарий с ID " + id + " не найден.", e);
    } catch (ArticleNotFoundException e) {
      throw new CommentDeleteException("Удаление невозможно: статья, связанная с комментарием, не найдена.", e);
    } catch (ArticleTagsCountExceedHeaderException | ArticleTagLengthExceedHeaderException | ArticleHeaderExceedHeaderException e) {
      throw new CommentDeleteException("Удаление невозможно: превышены ограничения для данных статьи.", e);
    }
  }
}
