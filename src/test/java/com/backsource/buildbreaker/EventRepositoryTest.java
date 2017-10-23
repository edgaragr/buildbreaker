package com.backsource.buildbreaker;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backsource.buildbreaker.models.Event;
import com.backsource.buildbreaker.repositories.EventRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class EventRepositoryTest {
	
	@Autowired
	private EventRepository repository;
	private Event event;
	
	@Before
	public void prepare() {
		event = new Event();
		event.setName("Edgar Garcia");
		event.setAmount(50.0);
		event.setDescription("Esto es una prueba");
		event.setDate(LocalDateTime.now());
		
		//given
		repository.save(event);
	}
	
	@Test
	public void whenFindByName_thenReturnEvent() {		
		//when
		Event found = repository.findByName("Edgar Garcia");
		
		//then
		assertThat(found.getName()).isEqualTo(event.getName());
	}
	
	@After
	public void cleanUp() {
		if(repository.exists(event.getId())) {
			repository.delete(event);
		}
	}	
}