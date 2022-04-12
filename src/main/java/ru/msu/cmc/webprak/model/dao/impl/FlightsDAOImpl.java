package ru.msu.cmc.webprak.model.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.msu.cmc.webprak.model.HibernateConfiguration;
import ru.msu.cmc.webprak.model.dao.FlightsDAO;
import ru.msu.cmc.webprak.model.entity.Flights;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
        if (filter.getTimeDep() != null) {
            LocalDateTime pattern = LocalDateTime.parse("%" + filter.getTimeDep() + "%");
            predicates.add(builder.or(builder.isNull(root.get("timeDep")),
                    builder.lessThanOrEqualTo(root.get("timeDep").as(LocalDateTime.class), pattern)));
        }
        if (filter.getTimeArr() != null) {
            LocalDateTime pattern = LocalDateTime.parse("%" + filter.getTimeArr() + "%");
            predicates.add(builder.or(builder.isNull(root.get("timeArr")),
                    builder.lessThanOrEqualTo(root.get("timeArr").as(LocalDateTime.class), pattern)));
        }
        if (filter.getFlightCost() != null) {
            Integer flightCost = filter.getFlightCost();
            predicates.add(builder.ge(root.get("flightCost"), builder.literal(flightCost)));
        }
        if (filter.getCntSeats() != null) {
            Integer cntSeats = filter.getCntSeats();
            predicates.add(builder.ge(root.get("cntSeats"), builder.literal(cntSeats)));
        }
        if (filter.getCntAvailableSeats() != null) {
            Integer cntAvailableSeats = filter.getCntAvailableSeats();
            predicates.add(builder.ge(root.get("cntAvailableSeats"), builder.literal(cntAvailableSeats)));
        }

        List<Flights> result = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        return result;
    }
}
