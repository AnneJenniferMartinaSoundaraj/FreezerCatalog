package com.springboot.northrow.freezer.catalog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.northrow.freezer.catalog.dao.FreezerCatalogDAO;
import com.springboot.northrow.freezer.catalog.entities.FoodItems;
import com.springboot.northrow.freezer.catalog.exceptions.FreezerCatalogRequestException;

/**
 * @author Anne Martina
 *
 */
@Service
public class FreezerCatalogService {

	@Autowired
	private FreezerCatalogDAO freezerCatalogDAO;

	/**
	 * @param item
	 * @return
	 */
	public FoodItems createOrUpdateItem(FoodItems item) {
		return freezerCatalogDAO.save(item);
	}

	/**
	 * @param id
	 * @return
	 */
	public FoodItems getItemById(Integer id) {
		return freezerCatalogDAO.findById(id).orElseThrow(
				() -> new FreezerCatalogRequestException(
						"Item Id can not be found:" + id));
	}

	/**
	 * @return
	 */
	public List<FoodItems> findAll() {
		return (List<FoodItems>) freezerCatalogDAO.findAll();
	}

	/**
	 * @param name
	 * @return
	 */
	public List<FoodItems> getItemByName(String name) {
		return freezerCatalogDAO.findByName(name);
	}

	/**
	 * @param type
	 * @return
	 */
	public List<FoodItems> getItemByType(String type) {
		return freezerCatalogDAO.findByType(type);
	}

	/**
	 * @param date
	 * @return
	 */
	public List<FoodItems> getItemByDate(Date date) {
		return freezerCatalogDAO.findByDate(date);
	}

}
