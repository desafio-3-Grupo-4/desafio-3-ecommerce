package application.DAO;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author gabriel
 *
 * Oct 17, 2013
 */

@SuppressWarnings("unchecked")
public class GenericDAO<PK, T> {
    @PersistenceContext
    private EntityManager entityManager;

    public GenericDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public T getById(PK pk) {
        return (T) entityManager.find(getTypeClass(), pk);
    }

    public void persist(T entity) {
        entityManager.persist(entity);

    }

    public void merge(T entity) {
            entityManager.merge(entity);

    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }

    public void removeById(PK id){
        try {
            T p1 = getById(id);

            remove(p1);
        } catch (Exception ex){
            System.out.println("Remove By Id Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<T> findAll() {
        return entityManager.createQuery(("FROM " + getTypeClass().getName()))
                .getResultList();
    }

    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
}