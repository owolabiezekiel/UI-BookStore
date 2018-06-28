package com.fitn.uibooks.repository;

import com.fitn.uibooks.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class BookRepository {

    @PersistenceContext (unitName = "bookStorePU")
    private EntityManager entityManager;

    public Book find(Long id){
        return entityManager.find(Book.class, id);
    }

    @Transactional(REQUIRED)
    public Book create(Book book){
        entityManager.persist(book);
        return book;
    }

    @Transactional(REQUIRED)
    public void delete(Long id){
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
