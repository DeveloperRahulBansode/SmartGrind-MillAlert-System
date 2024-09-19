package com.mill.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "flour_order")
public class FlourOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "order_status")
    private String orderStatus;

	public FlourOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlourOrder(Long id, String clientName, String contactNumber, String orderStatus) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.contactNumber = contactNumber;
		this.orderStatus = orderStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "FlourOrder [id=" + id + ", clientName=" + clientName + ", contactNumber=" + contactNumber
				+ ", orderStatus=" + orderStatus + "]";
	}

    
}
