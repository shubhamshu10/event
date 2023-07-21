package com.event.Eventorganize.dto;

//import com.event.Eventorganize.entity.EventCategory;

import lombok.Data;
@Data
public class EventDto {
	private long id;
	private String firstname;
	private String lastname;
	private String name;
	private String city;
	private long contact;
	private String date;
    private int categoryId;
}
