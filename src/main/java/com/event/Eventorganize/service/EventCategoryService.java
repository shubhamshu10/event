package com.event.Eventorganize.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.Eventorganize.entity.EventCategory;
import com.event.Eventorganize.repository.EventCategoryRepository;


@Service
public class EventCategoryService {

	@Autowired
    private EventCategoryRepository eventCategoryRepo;
	
    public List<EventCategory> getEventCategory(){
  	  return eventCategoryRepo.findAll();
    }
    public void addEventCategory(EventCategory eventCategory) {
  	  eventCategoryRepo.save(eventCategory);
    }
    public void removeEventCategoryById(int id) {
  	  eventCategoryRepo.deleteById(id);
    }
    public Optional<EventCategory> getEventCategoryById(int id) {
  	  return eventCategoryRepo.findById(id);
    }
}
