package com.jeecg.cms.entity;

import java.io.Serializable;
import java.util.Date;

public class ErpProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private String id;
	/**
	 * NAME
	 */
	private String name;
	/**
	 * COLUMN_ID
	 */
	private String columnId;

	/**
	 * CODE
	 */
	private String code;

	/**
	 * ONECODE
	 */
	private String oneCode;

	/**
	 * LOCATION
	 */
	private String location;

	/**
	 * IN_DATE
	 */
	private Date inDate;

	/**
	 * BRAND
	 */
	private String brand;

	/**
	 * MODEL
	 */
	private String model;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOneCode() {
		return oneCode;
	}

	public void setOneCode(String oneCode) {
		this.oneCode = oneCode;
	}

}
