package team.sevendwarfs.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by deng on 2017/4/25.
 *
 * @param <T> the type parameter
 */
public abstract class AbstractJpaDAO<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Sets clazz.
     *
     * @param clazzToSet the clazz to set
     */
    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    /**
     * Find one t.
     *
     * @param id the id
     * @return the t
     */
    public T findOne(final Integer id) {
        return entityManager.find(clazz, id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    /**
     * Create.
     *
     * @param entity the entity
     */
    public void create(final T entity) {
        entityManager.persist(entity);
    }

    /**
     * Update t.
     *
     * @param entity the entity
     * @return the t
     */
    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    /**
     * Delete.
     *
     * @param entity the entity
     */
    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    /**
     * Delete by id.
     *
     * @param entityId the entity id
     */
    public void deleteById(final Integer entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

}
