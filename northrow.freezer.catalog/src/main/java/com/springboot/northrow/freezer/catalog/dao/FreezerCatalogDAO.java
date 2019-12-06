package com.springboot.northrow.freezer.catalog.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springboot.northrow.freezer.catalog.entities.FoodItems;

public interface FreezerCatalogDAO extends CrudRepository<FoodItems, Integer> {
	// method to retrieve item by name
	List<FoodItems> findByName(String name);
	//method to retrieve item by type
	List<FoodItems> findByType(String type);
	//method to retrieve item by date
	List<FoodItems> findByDate(Date date);
}
