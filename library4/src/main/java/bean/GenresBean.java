package bean;

import entity.Genre;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class GenresBean extends AbstractBean<Genre> {
    
    @PersistenceContext
    EntityManager em;

    public GenresBean() {
        super(Genre.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
