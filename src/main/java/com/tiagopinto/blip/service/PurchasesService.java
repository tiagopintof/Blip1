package com.tiagopinto.blip.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;

import com.tiagopinto.blip.model.Detail;
import com.tiagopinto.blip.model.Purchase;
import com.tiagopinto.blip.repository.DetailsRepository;
import com.tiagopinto.blip.repository.PurchaseRepository;

public class PurchasesService {
	
	@Inject
	private PurchaseRepository purchaseRepository;
	
	@Inject
	private DetailsRepository detailsRepository;
	
	public List<Purchase> getPurchasesNotExpired(DateTime date){
		List<Purchase> purchases = new ArrayList<Purchase>();
		
		List<Purchase> allPurchases = purchaseRepository.findAll();
		
		List<Long> purchaseIds = new ArrayList<Long>();
		for(Purchase p : allPurchases){
			if(p.getExpires().isAfter(date.getMillis())){
				purchaseIds.add(p.getId());
				purchases.add(p);
			}
		}
		
		List<Detail> allDetails = detailsRepository.findByPurchaseIdIn(purchaseIds);
		
		for(Detail d : allDetails){
			for(Purchase p : purchases){
				if(p.getId().equals(d.getPurchaseId())){
					p.getPurchaseDetails().add(d);
				}
			}
		}
		
		return purchases;
	}

	public void savePurchase(Purchase purchase){
		purchaseRepository.savePurchase(purchase);
	}
}
