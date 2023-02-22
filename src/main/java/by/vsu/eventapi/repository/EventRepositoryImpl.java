package by.vsu.eventapi.repository;

import by.vsu.eventapi.exception.EventNotFoundException;
import by.vsu.eventapi.model.Event;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EventRepositoryImpl implements EventRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Event save(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(event);
        return event;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Event update(Event event) throws EventNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        Optional<Event> id = findById(event.getId());
        if (id.isEmpty()) {
            EventNotFoundException.call(event.getId());
        }
        session.merge(event);
        return event;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Optional<Event> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Event desiredEvent = session.get(Event.class, id);
        return Optional.ofNullable(desiredEvent);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Event> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> root = criteriaQuery.from(Event.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteById(Long id) throws EventNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        Optional<Event> markedToDeletionEvent = findById(id);
        if (markedToDeletionEvent.isEmpty()) {
            EventNotFoundException.call(id);
        }
        session.remove(markedToDeletionEvent.get());
    }
}
