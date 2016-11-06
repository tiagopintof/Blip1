package com.tiagopinto.blip.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Purchase {
	
	private Long id;
	private String productType;
	private DateTime expires;
	private List<Detail> purchaseDetails = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public DateTime getExpires() {
		return expires;
	}
	public void setExpires(DateTime expires) {
		this.expires = expires;
	}
	public List<Detail> getPurchaseDetails() {
		return purchaseDetails;
	}
	public void setPurchaseDetails(List<Detail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expires == null) ? 0 : expires.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		if (expires == null) {
			if (other.expires != null)
				return false;
		} else if (!expires.equals(other.expires))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", productType=" + productType + ", expires=" + expires + "]";
	}
	

}
