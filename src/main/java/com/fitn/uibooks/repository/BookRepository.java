package com.fitn.uibooks.repository;

import com.fitn.uibooks.Util.NumberGenerator;
import com.fitn.uibooks.Util.TextUtils;
import com.fitn.uibooks.model.Book;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class BookRepository {

    @PersistenceContext (unitName = "bookStorePU")
    private EntityManager entityManager;

    @Inject
    TextUtils textUtils;
    @Inject
    NumberGenerator isbnGenerator;

    public Book find(@NotNull  Long id){
        return entityManager.find(Book.class, id);
    }

    @Transactional(REQUIRED)
    public Book create(@NotNull Book book){
        book.setTitle(textUtils.sanitize(book.getTitle()));
        book.setIsbn(isbnGenerator.generateNumber());
        entityManager.persist(book);
        return book;
    }

    @Transactional(REQUIRED)
    public void delete(@NotNull Long id){
        entityManager.remove(entityManager.getReference(Book.class, id));
    }

    public List<Book> findAll(){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b from Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    public Long getCount(){
        TypedQuery<Long> query = entityManager.createQuery("SELECT count(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }
}
