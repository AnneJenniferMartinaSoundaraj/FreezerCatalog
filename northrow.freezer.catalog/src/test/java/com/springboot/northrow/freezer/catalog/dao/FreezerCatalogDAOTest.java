package com.springboot.northrow.freezer.catalog.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.northrow.freezer.catalog.entities.FoodItems;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FreezerCatalogDAOTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private FreezerCatalogDAO freezerCatalogDAO;

	@Test
	public void saveItem() {
		FoodItems item = getFoodItem();
		FoodItems savedItemInDb = entityManager.persist(item);
		FoodItems getItemFromDb = freezerCatalogDAO.findById(
				savedItemInDb.getId()).get();
		assertThat(getItemFromDb).isEqualTo(savedItemInDb);

	}

	@Test
	public void testGetAllItems() {
		FoodItems item1 = getFoodItem();

		FoodItems newItem = new FoodItems();
		newItem.setName("Mango");
		newItem.setQuantity(8);
		newItem.setType("Fruit");

		// Save both Items in DB
		entityManager.persist(item1);
		entityManager.persist(newItem);

		Iterable<FoodItems> allItemsFromDb = freezerCatalogDAO.findAll();
		List<FoodItems> itemList = new ArrayList<>();

		for (FoodItems items : allItemsFromDb) {
			itemList.add(items);
		}
		assertThat(itemList.size()).isEqualTo(2);
	}

	@Test
	public void testFindByName() {
		FoodItems item = getFoodItem();

		// Persist Item in DB
		entityManager.persist(item);

		// Get Item info from DB by specific name
		FoodItems getFromDb = freezerCatalogDAO.findByName("PineApple").get(0);
		assertThat(getFromDb.getQuantity()).isEqualTo(3);
	}

	@Test
	public void testUpdateItem() {
		FoodItems item = getFoodItem();

		// Persist Item in DB
		entityManager.persist(item);

		FoodItems getFromDb = freezerCatalogDAO.findByName("PineApple").get(0);
		// update quantity
		getFromDb.setQuantity(9);
		entityManager.persist(getFromDb);

		assertThat(getFromDb.getQuantity()).isEqualTo(9);
	}

	private FoodItems getFoodItem() {
		FoodItems newItem = new FoodItems();
		newItem.setName("PineApple");
		newItem.setQuantity(3);
		newItem.setType("Fruit");
		return newItem;
	}
}
