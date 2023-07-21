package com.event.Eventorganize.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.Eventorganize.entity.Event;
import com.event.Eventorganize.repository.EventRepository;

@Service
public class EventService {
	@Autowired
    private EventRepository repo;
	
	public List<Event> getAllEvent(){
		return repo.findAll();		
	}
	public void addEvent(Event event) {
		repo.save(event);
	}
	public void removeEvent(long id) {
		repo.deleteById(id);
	}
	public Optional<Event> getEventById(long id) {
		return repo.findById(id);
	}
	public List<Event> getEventByCategoryId(int category_id){
		return repo.findAllByCategoryId(category_id);
	}
}
