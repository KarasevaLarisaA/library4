package bean;

import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractBean<T> implements Serializable {

    private final Class<T> entityClass;

    public AbstractBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public List<T> findAll() {
        var cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public T findById(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    protected abstract EntityManager getEntityManager();
}
