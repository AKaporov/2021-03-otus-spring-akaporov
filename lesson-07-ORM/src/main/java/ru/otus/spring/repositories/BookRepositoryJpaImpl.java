package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.exception.RepositoryException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {
    private static final String BOOK_EXCEPTION_CAPTION = "The book with title \"%s\"";
    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            // TODO: 24.06.2021 Потому что по документации persist() сразу не выдает Id,
            //  только когда ИЛИ сессия закончилась ИЛИ после flush() ИЛИ завершили транзакцию
            em.flush();
            return book;
        }

        return em.merge(book);
    }

    @Override
    public void remove(Book book) {
        try {
            em.remove(book);
        } catch (IllegalArgumentException e) {
            String message = String.format(BOOK_EXCEPTION_CAPTION + " is not an entity or is a detached entity", book.getTitle());
            throw new RepositoryException(message);
        } catch (Exception e) {
            String message = String.format(BOOK_EXCEPTION_CAPTION + " was not found in the database.", book.getTitle());
            throw new RepositoryException(message);
        }
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", title);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b " +
                        "  from Book b " +
                        "  join fetch b.author a " +
                        "  join fetch b.genre g " +
                        " where 1 = 1", Book.class);
        return query.getResultList();
    }
}
