package com.backsource.buildbreaker.repositories;

import com.backsource.buildbreaker.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, String> {

    @Override
    Event findOne(String id);

    @Override
    void delete(Event deleted);
    
    Event findByName(String name);
}