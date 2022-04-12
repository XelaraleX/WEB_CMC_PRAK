package ru.msu.cmc.webprak.model.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.msu.cmc.webprak.model.HibernateConfiguration;
import ru.msu.cmc.webprak.model.dao.FlightsDAO;
import ru.msu.cmc.webprak.model.entity.Flights;
import ru.msu.cmc.webprak.utils.TimeConvertUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
public class FlightsDAOImpl extends BaseDAOImpl<Flights> implements FlightsDAO {

    public FlightsDAOImpl() {
        super(Flights.class);
    }

    @Override
    public Collection<Flights> getFlightsByFilter(Filter filter) throws HibernateException {
        Session session = HibernateConfiguration.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flights> criteriaQuery = builder.createQuery(Flights.class);
        Root<Flights> root = criteriaQuery.from(Flights.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getTimeDepMin() != null) {
            String str = filter.getTimeDepMin();
            predicates.add(builder.lessThan(root.get("timeDep"), TimeConvertUtil.fromString(str)));
        }
        if (filter.getTimeDepMax() != null) {
            String str = filter.getTimeDepMax();
            predicates.add(builder.greaterThan(root.get("timeDep"), TimeConvertUtil.fromString(str)));
        }
        if (filter.getTimeArrMin() != null) {
            String str = filter.getTimeArrMin();
            predicates.add(builder.lessThan(root.get("timeArr"), TimeConvertUtil.fromString(str)));
        }
        if (filter.getTimeArrMax() != null) {
            String str = filter.getTimeArrMax();
            predicates.add(builder.greaterThan(root.get("timeArr"), TimeConvertUtil.fromString(str)));
        }
        if (filter.getFlightCostMin() != null) {
            Integer flightCostMin = filter.getFlightCostMin();
            predicates.add(builder.le(builder.literal(flightCostMin), root.get("flightCost")));
        }
        if (filter.getFlightCostMax() != null) {
            Integer flightCostMax = filter.getFlightCostMax();
            predicates.add(builder.ge(builder.literal(flightCostMax), root.get("flightCost")));
        }
        if (filter.getCntSeatsMin() != null) {
            Integer cntSeatsMin = filter.getCntSeatsMin();
            predicates.add(builder.le(builder.literal(cntSeatsMin), root.get("cntSeats")));
        }
        if (filter.getCntSeatsMax() != null) {
            Integer cntSeatsMax = filter.getCntSeatsMax();
            predicates.add(builder.ge(builder.literal(cntSeatsMax), root.get("cntSeats")));
        }
        if (filter.getCntAvailableSeatsMin() != null) {
            Integer cntAvailableSeatsMin = filter.getCntAvailableSeatsMin();
            predicates.add(builder.le(builder.literal(cntAvailableSeatsMin), root.get("cntAvailableSeats")));
        }
        if (filter.getCntAvailableSeatsMax() != null) {
            Integer cntAvailableSeatsMax = filter.getCntAvailableSeatsMax();
            predicates.add(builder.ge(builder.literal(cntAvailableSeatsMax), root.get("cntAvailableSeats")));
        }

        if (predicates.size() != 0)
            criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Flights> result = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        return result;
    }
}
