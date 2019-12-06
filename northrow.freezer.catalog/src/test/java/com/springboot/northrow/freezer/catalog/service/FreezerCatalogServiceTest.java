package com.springboot.northrow.freezer.catalog.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.northrow.freezer.catalog.dao.FreezerCatalogDAO;
import com.springboot.northrow.freezer.catalog.entities.FoodItems;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreezerCatalogServiceTest {

	@Autowired
	private FreezerCatalogService freezerCatalogService;

	@MockBean
	private FreezerCatalogDAO freezerCatalogDAO;

	@Test
	public void testCreateItem() {

		FoodItems item = getFoodItem();

		Mockito.when(freezerCatalogDAO.save(item)).thenReturn(item);

		assertThat(freezerCatalogService.createOrUpdateItem(item)).isEqualTo(
				item);

	}

	@Test
	public void testGetAllItems() {
		FoodItems item = getFoodItem();

		FoodItems newItem = new FoodItems();
		newItem.setName("PineApple");
		newItem.setQuantity(3);
		newItem.setType("Fruit");

		List<FoodItems> itemList = new ArrayList<>();
		itemList.add(item);
		itemList.add(newItem);

		Mockito.when(freezerCatalogDAO.findAll()).thenReturn(itemList);

		assertThat(freezerCatalogService.findAll()).isEqualTo(itemList);
	}

	@Test
	public void testUpdateItem() {
		FoodItems item = getFoodItem();

		Mockito.when(freezerCatalogDAO.save(item)).thenReturn(item);
		Mockito.when(freezerCatalogDAO.findById(1).get()).thenReturn(item);

		item.setQuantity(7);

		Mockito.when(freezerCatalogDAO.save(item)).thenReturn(item);

		assertThat(freezerCatalogService.createOrUpdateItem(item)).isEqualTo(
				item);

	}

	@Test
	public void testGetItemByType() {

		FoodItems item = getFoodItem();
		Mockito.when(freezerCatalogDAO.save(item)).thenReturn(item);

		Mockito.when(freezerCatalogDAO.findByType("Fruit").get(0)).thenReturn(
				item);

		assertThat(freezerCatalogService.getItemByType("Fruit"))
				.isEqualTo(item);
	}

	private FoodItems getFoodItem() {
		FoodItems newItem = new FoodItems();
		newItem.setName("PineApple");
		newItem.setQuantity(3);
		newItem.setType("Fruit");
		return newItem;
	}
}
