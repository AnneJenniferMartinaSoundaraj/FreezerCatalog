package com.springboot.northrow.freezer.catalog.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.northrow.freezer.catalog.entities.FoodItems;
import com.springboot.northrow.freezer.catalog.service.FreezerCatalogService;

@RestController
@RequestMapping(value = "/api")
public class FreezerCatalogController {

	@Autowired
	private FreezerCatalogService freezerCatalogService;

	/**
	 * Method to create an item
	 * 
	 * @param item
	 * @return
	 */
	@PostMapping(value = "/createItem")
	public FoodItems createItem(@RequestBody FoodItems item) {
		return freezerCatalogService.createOrUpdateItem(item);

	}

	/**
	 * Method to getall food items
	 * 
	 * @return
	 */
	@GetMapping("/items")
	public List<FoodItems> getAllFoodItems() {
		return freezerCatalogService.findAll();
	}

	/**
	 * method to retrieve item by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = ("/getItemById/{id}"))
	public FoodItems getItemById(@PathVariable("id") Integer id) {
		return freezerCatalogService.getItemById(id);

	}

	/**
	 * method to update an item
	 * 
	 * @param id
	 * @param itemDetails
	 * @return
	 */
	@PutMapping(value = ("/updateItem/{id}"))
	public FoodItems updateItem(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody FoodItems itemDetails) {

		FoodItems item = freezerCatalogService.getItemById(id);

		item.setName(itemDetails.getName());
		item.setQuantity(itemDetails.getQuantity());
		item.setType(itemDetails.getType());
		item.setDate(itemDetails.getDate());

		FoodItems updatedItems = freezerCatalogService.createOrUpdateItem(item);
		return updatedItems;
	}

	/**
	 * method to retrieve item by name
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping(value = ("/getItemByName/{name}"))
	public List<FoodItems> getItemByName(@PathVariable("name") String name) {
		return freezerCatalogService.getItemByName(name);
	}

	/**
	 * method to retrieve item by type
	 * 
	 * @param type
	 * @return
	 */
	@GetMapping(value = ("/getItemByType/{type}"))
	public List<FoodItems> getItemByType(@PathVariable("type") String type) {
		return freezerCatalogService.getItemByType(type);
	}

	/**
	 * method to retrieve item by Date
	 * 
	 * @param date
	 * @return
	 */
	@GetMapping(value = ("/getItemByDate/{date}"))
	public List<FoodItems> getItemByDate(@PathVariable("date") Date date) {
		return freezerCatalogService.getItemByDate(date);
	}
}
