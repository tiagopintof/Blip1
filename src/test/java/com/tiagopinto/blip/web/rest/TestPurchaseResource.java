package com.tiagopinto.blip.web.rest;

import static org.mockito.MockitoAnnotations.initMocks;

import org.joda.time.DateTime;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tiagopinto.blip.model.Purchase;
import com.tiagopinto.blip.service.PurchasesService;

import static org.mockito.Mockito.*;

public class TestPurchaseResource {
	
	@InjectMocks
	private PurchaseResource purchaseResource;
	
	@Mock
	private PurchasesService purchaseService;
	
	@BeforeClass
    public void before() {
        initMocks(this);
	}
	
	@Test
	public void getPurchasesTest(){
		DateTime date = new DateTime();
		
		purchaseResource.getPurchases(date);
		
		verify(purchaseService, times(1)).getPurchasesNotExpired(date);
	}

	@Test
	public void savePurchaseTest(){
		Purchase purchase = new Purchase();
		
		purchaseResource.savePurchase(purchase);
		
		verify(purchaseService, times(1)).savePurchase(purchase);
	}
}
