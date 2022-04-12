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
        if (filter.getCntKmMin() != null) {
            Integer cntKmMin = filter.getCntKmMin();
            predicates.add(builder.le(builder.literal(cntKmMin), root.get("cntKm")));
        }
        if (filter.getCntKmMax() != null) {
            Integer cntKmMax = filter.getCntKmMax();
            predicates.add(builder.ge(builder.literal(cntKmMax), root.get("cntKm")));
        }
        if (filter.getCntUseKmMin() != null) {
            Integer cntUseKmMin = filter.getCntUseKmMin();
            predicates.add(builder.le(builder.literal(cntUseKmMin), root.get("cntUseKm")));
        }
        if (filter.getCntUseKmMax() != null) {
            Integer cntUseKmMax = filter.getCntUseKmMax();
            predicates.add(builder.ge(builder.literal(cntUseKmMax), root.get("cntUseKm")));
        }

        if (predicates.size() != 0)
            criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<BonusProgram> result = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        return result;
    }
}
