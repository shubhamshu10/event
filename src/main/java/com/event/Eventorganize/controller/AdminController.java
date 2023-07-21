package com.event.Eventorganize.controller;


import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;

import com.event.Eventorganize.dto.EventDto;
import com.event.Eventorganize.entity.Event;
import com.event.Eventorganize.entity.EventCategory;
import com.event.Eventorganize.service.EventCategoryService;
import com.event.Eventorganize.service.EventService;
//import com.pnp.dto.ProductDto;
//import com.pnp.entity.Product;
//import com.pnp.dto.ProductDto;


//import lombok.Getter;

@Controller
public class AdminController {
	@Autowired
    EventCategoryService cateService;
	@Autowired
	EventService service;
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/admin/category")
	public String getCategory(Model model) {
		model.addAttribute("categories", cateService.getEventCategory());
		return "category";
	}
	 @GetMapping("/admin/category/add")
	    public String addCategory(Model model) {
	    	model.addAttribute("categories", new EventCategory());
	    	return "categoriesAdd";
	    }
	   @PostMapping("/admin/category/add")
	    public String saveCategory(@ModelAttribute("category") EventCategory category ) {
	    	cateService.addEventCategory(category);
	    	return "redirect:/admin/category";
	    }
	    @GetMapping("/admin/category/delete/{id}")
	    public String deleteCategory(@PathVariable int id) {
	    	cateService.removeEventCategoryById(id);
	    	return "redirect:/admin/category";
	    }
	    @GetMapping("/admin/category/update/{id}")
	    public String updateCategory(@PathVariable int id,Model model) {
	    	Optional<EventCategory> category=cateService.getEventCategoryById(id);
	    	if(category.isPresent()) {
	    		model.addAttribute("categories", category.get());
	    		return "categoriesAdd";
	    	}else {
	    		return "404 category not found";
	    	}
	    	
	    }
	    // Events
	    @GetMapping("/admin/event")
	    public String getEvent(Model model) {
	    	model.addAttribute("events", service.getAllEvent());
	    	return "event";
	    }
	    @GetMapping("/admin/event/add")
	    public String addEvent(Model model) {
	    	model.addAttribute("eventDTO", new EventDto());
	    	model.addAttribute("categories", cateService.getEventCategory());
	    	return "addEvent";
	    }
	    
	    @PostMapping("/admin/event/add")
	    public String saveEvent(@ModelAttribute("eventDto") EventDto eventDto){
	        Event event = new Event();
	        event.setId(eventDto.getId());
	        event.setFirstname(eventDto.getFirstname());
	        event.setLastname(eventDto.getLastname());
	        event.setContact(eventDto.getContact());
	        event.setName(eventDto.getName());
	        event.setCity(eventDto.getCity());
	        event.setDate(eventDto.getDate());
            event.setCategory(cateService.getEventCategoryById(eventDto.getCategoryId()).get());
	       
	        service.addEvent(event);
	    	return "redirect:/admin/event";
	    }
	    @GetMapping("/admin/event/delete/{id}")
	    public String deleteEvent(@PathVariable long id) {
	    	service.removeEvent(id);
	    	return "redirect:/admin/event";
	    }
	    @GetMapping("/admin/event/update/{id}")
	    public String updateEvent(@PathVariable long id,Model model) {
	    	Event event=service.getEventById(id).get();
	    	EventDto dto = new EventDto();
	    	dto.setId(event.getId());
	    	dto.setName(event.getName());
	    	dto.setFirstname(event.getFirstname());
	    	dto.setLastname(event.getName());
	    	dto.setContact(event.getContact());
	    	dto.setDate(event.getDate());
	    	dto.setCity(event.getCity());
	    	dto.setCategoryId(event.getCategory().getId());
	    	model.addAttribute("categories", cateService.getEventCategory());
	    	model.addAttribute("eventDTO", dto);
	    	return "addEvent";
	    }
}
