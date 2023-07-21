package com.event.Eventorganize.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.Eventorganize.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
    List<Event> findAllByCategoryId(int category_id);
}
