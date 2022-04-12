package ru.msu.cmc.webprak.model.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.msu.cmc.webprak.model.HibernateConfiguration;
import ru.msu.cmc.webprak.model.dao.BonusProgramDAO;
import ru.msu.cmc.webprak.model.entity.BonusProgram;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
public class BonusProgramDAOImpl extends BaseDAOImpl<BonusProgram> implements BonusProgramDAO {

    public BonusProgramDAOImpl() {
        super(BonusProgram.class);
    }

    @Override
    public Collection<BonusProgram> getBonusProgramByFilter(Filter filter) throws HibernateException {
        Session session = HibernateConfiguration.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<BonusProgram> criteriaQuery = builder.createQuery(BonusProgram.class);
        Root<BonusProgram> root = criteriaQuery.from(BonusProgram.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getBonusCard() != null) {
            String pattern = "%" + filter.getBonusCard() + "%";
            predicates.add(builder.like(root.get("bonusCard"), pattern));
        }
        if (filter.getCntKm() != null) {
            Integer cntKm = filter.getCntKm();
            predicates.add(builder.ge(root.get("cntKm"), builder.literal(cntKm)));
        }
        if (filter.getCntUseKm() != null) {
            Integer cntUseKm = filter.getCntUseKm();
            predicates.add(builder.ge(root.get("cntUseKm"), builder.literal(cntUseKm)));
        }

        List<BonusProgram> result = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        return result;
    }
}
