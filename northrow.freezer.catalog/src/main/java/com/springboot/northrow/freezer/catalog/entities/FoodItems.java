package com.springboot.northrow.freezer.catalog.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "foodItems")
public class FoodItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Integer id;
	@Column(name = "item_name", nullable = false)
	private String name;
	@Column(name = "item_type")
	private String type;
	@Column(name = "item_quantity")
	private long quantity;
	@Column(name = "date_added")
	private Date date;

	public FoodItems() {

	}

	/**
	 * @param id
	 * @param name
	 * @param type
	 * @param quantity
	 * @param date
	 */
	public FoodItems(Integer id, String name, String type, long quantity,
			Date date) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.date = date;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
