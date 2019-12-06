package com.springboot.northrow.freezer.catalog;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.springboot.northrow.freezer.catalog.entities.FoodItems;
import com.springboot.northrow.freezer.catalog.service.FreezerCatalogService;

@SpringBootApplication
public class FreezerCatalog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication
				.run(FreezerCatalog.class, args);
		FreezerCatalogService service = applicationContext.getBean(
				"freezerCatalogService", FreezerCatalogService.class);
		FoodItems item = new FoodItems();
		item.setName("Milk");
		item.setType("Diary");
		item.setQuantity(2);
		item.setDate(new Date());

		service.createOrUpdateItem(item);
	}

}
