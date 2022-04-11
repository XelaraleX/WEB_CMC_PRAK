package ru.msu.cmc.webprak.model.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.msu.cmc.webprak.model.HibernateDatabaseConfig;
import ru.msu.cmc.webprak.model.dao.UsersDAO;
import ru.msu.cmc.webprak.model.entity.Users;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
public class UsersDAOImpl extends BaseDAOImpl<Users> implements UsersDAO {

    public UsersDAOImpl() {
        super(Users.class);
    }

    @Override
    public Collection<Users> getUsersByFilter(Filter filter) throws HibernateException {
        Session session = HibernateDatabaseConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Users> criteriaQuery = builder.createQuery(Users.class);
        Root<Users> root = criteriaQuery.from(Users.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getUserStatus() != null) {
            String pattern = "%" + filter.getUserStatus() + "%";
            predicates.add(builder.like(root.get("userStatus"), pattern));
        }
        if (filter.getFullName() != null) {
            String pattern = "%" + filter.getFullName() + "%";
            predicates.add(builder.like(root.get("fullName"), pattern));
        }
        if (filter.getAddress() != null) {
            String pattern = "%" + filter.getAddress() + "%";
            predicates.add(builder.like(root.get("address"), pattern));
        }
        if (filter.getEmail() != null) {
            String pattern = "%" + filter.getEmail() + "%";
            predicates.add(builder.like(root.get("email"), pattern));
        }
        if (filter.getPhoneNumber() != null) {
            String pattern = "%" + filter.getPhoneNumber() + "%";
            predicates.add(builder.like(root.get("phoneNumber"), pattern));
        }
        if (filter.getUserLogin() != null) {
            String pattern = "%" + filter.getUserLogin() + "%";
            predicates.add(builder.like(root.get("userLogin"), pattern));
        }
        if (filter.getUserPassword() != null) {
            String pattern = "%" + filter.getUserPassword() + "%";
            predicates.add(builder.like(root.get("userPassword"), pattern));
        }

        List<Users> result = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        return result;
    }
}
