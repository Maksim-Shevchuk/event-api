package by.vsu.eventapi.repository;

import by.vsu.eventapi.exception.EventNotFoundException;
import by.vsu.eventapi.model.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepositoryImpl implements EventRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public Event save(Event event) {
        entityManager.persist(event);
        return event;
    }

    @Transactional
    @Override
    public Event update(Event event) throws EventNotFoundException {
        Long eventId = event.getId();
        Optional<Event> expectedEvent = findById(eventId);
        if (expectedEvent.isEmpty()) {
            throw new EventNotFoundException(String.format("There is no such event with id %d", eventId));
        }
        return entityManager.merge(event);
    }

    @Transactional
    @Override
    public Optional<Event> findById(long id) {
        return Optional.ofNullable(entityManager.find(Event.class, id));
    }

    @Transactional
    @Override
    public List<Event> findAll(int size, int page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> root = criteriaQuery.from(Event.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
        criteriaQuery.select(root);
        Query query = entityManager.createQuery(criteriaQuery);
        return query.setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Transactional
    @Override
    public void deleteById(long id) throws EventNotFoundException {
        Optional<Event> event = findById(id);
        if (event.isEmpty()) {
            throw new EventNotFoundException(String.format("There is no such event with id %d", id));
        }
        entityManager.remove(event.get());
    }
}