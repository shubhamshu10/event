package com.event.Eventorganize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.event.Eventorganize.dto.EventDto;
import com.event.Eventorganize.entity.Event;
import com.event.Eventorganize.service.EventCategoryService;
import com.event.Eventorganize.service.EventService;
//import com.pnp.global.GlobalData;



@Controller
public class HomeController {
	@Autowired
	EventService service;
	@Autowired
	EventCategoryService cateService;
	@GetMapping({"/home"})	
	   public String home(Model model) {
		   //model.addAttribute("cartCount", GlobalData.cart.size());
		   return "index";
	   }
	@GetMapping("/newEvent")
	public String newEvent(Model model) {
		model.addAttribute("eventDTO", new EventDto());
    	model.addAttribute("categories", cateService.getEventCategory());
		return "newEvent";
	}
	
	 @PostMapping("/newEvent")
	    public String addNewEvent(@ModelAttribute("eventDto") EventDto eventDto){
	        Event event = new Event();
	     //   event.setId(eventDto.getId());
	        event.setFirstname(eventDto.getFirstname());
	        event.setLastname(eventDto.getLastname());
	        event.setContact(eventDto.getContact());
	        event.setName(eventDto.getName());
	        event.setCity(eventDto.getCity());
	        event.setDate(eventDto.getDate());
         event.setCategory(cateService.getEventCategoryById(eventDto.getCategoryId()).get());
	       
	        service.addEvent(event);
	    	return "success";
	    }
}
