package com.springboot.northrow.freezer.catalog.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.northrow.freezer.catalog.entities.FoodItems;
import com.springboot.northrow.freezer.catalog.service.FreezerCatalogService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FreezerCatalogController.class)
public class FreezerCatalogControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FreezerCatalogService freezerCatalogService;

	/**
	 * @throws Exception
	 */
	@Test
	public void testCreateItem() throws Exception {

		FoodItems mockItem = getFoodItem();

		String inputInJson = this.mapToJson(mockItem);

		String URI = "/api/createItem";

		Mockito.when(
				freezerCatalogService.createOrUpdateItem(Mockito
						.any(FoodItems.class))).thenReturn(mockItem);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetItemById() throws Exception {
		FoodItems mockItem = getFoodItem();

		Mockito.when(freezerCatalogService.getItemById(Mockito.anyInt()))
				.thenReturn(mockItem);

		String URI = "/api/getItemById/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockItem);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	private FoodItems getFoodItem() {
		FoodItems newItem = new FoodItems();
		newItem.setName("PineApple");
		newItem.setQuantity(3);
		newItem.setType("Fruit");
		return newItem;
	}
}
