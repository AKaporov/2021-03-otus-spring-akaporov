package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.exception.RepositoryException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {
    private static final String AUTHOR_EXCEPTION_CAPTION = "The author with name \"%s\"";
    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            // TODO: 24.06.2021 Потому что по документации persist() сразу не выдает Id,
            //  только когда ИЛИ сессия закончилась ИЛИ после flush() ИЛИ завершили транзакцию
            em.flush();
            return author;
        }

        return em.merge(author);
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Optional<Author> findByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a where 1 = 1", Author.class);
        return query.getResultList();
    }

    @Override
    public void remove(Author author) {
        try {
            em.remove(author);
        } catch (IllegalArgumentException e) {
            String message = String.format(AUTHOR_EXCEPTION_CAPTION + " is not an entity or is a detached entity", author.getName());
            throw new RepositoryException(message);
        } catch (Exception e) {
            String message = String.format(AUTHOR_EXCEPTION_CAPTION + " was not found in the database.", author.getName());
            throw new RepositoryException(message);
        }
    }
}
