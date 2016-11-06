package com.tiagopinto.blip.repository;

import java.util.List;

import com.tiagopinto.blip.model.Purchase;

public interface PurchaseRepository {
	
	List<Purchase> findAll();
	Purchase findById(Long id);
	
	void savePurchase(Purchase purchase);

}
