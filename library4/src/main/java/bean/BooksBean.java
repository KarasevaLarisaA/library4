package bean;

import entity.Book;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class BooksBean extends AbstractBean<Book> {
    
    @PersistenceContext
    EntityManager em;

    public BooksBean() {
        super(Book.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Book> findByGenre(Object genre) {
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(Book.class);
        var root = cq.from(Book.class);
        cq.select(root).where(cb.equal(root.get("genreId"), genre));
        return em.createQuery(cq).getResultList();
    }

}
