package com.tiagopinto.blip.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tiagopinto.blip.model.Detail;
import com.tiagopinto.blip.model.Purchase;
import com.tiagopinto.blip.repository.DetailsRepository;
import com.tiagopinto.blip.repository.PurchaseRepository;
import com.tiagopinto.blip.service.PurchasesService;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import static org.mockito.MockitoAnnotations.*;

public class TestPurchasesService {
	
	@InjectMocks
	private PurchasesService purchaseService;
	
	@Mock
	private PurchaseRepository purchaseRepository;
	
	@Mock
	private DetailsRepository detailsRepository;
	
	@BeforeClass
    public void before() {
        initMocks(this);
	}
	
	@Test
	public void getPurchasesNotExpiredTest(){
		DateTime date = new DateTime();
		List<Purchase> allPurchases = new ArrayList<>();
		
		
		for(int i= 1; i<=4; i++){
			Purchase purchase = new Purchase();
			purchase.setId(Long.valueOf(i));
			purchase.setExpires(i%2 == 0 ? date.minus(i * 1L) : date.plus(i * 1L));
			allPurchases.add(purchase);
		}
		
		Object[][] detailsCreate = {{10L,"detail1",1,1D,1L},
		                            {20L,"detail2",2,2D,1L},
		                            {30L,"detail3",3,3D,2L},
		                            {40L,"detail4",4,4D,2L},
		                            {50L,"detail5",5,5D,3L},
		                            {60L,"detail6",6,6D,3L},
		                            {70L,"detail7",7,7D,4L},
		                            {80L,"detail8",8,8D,4L}
		};
		
		List<Detail> details = new ArrayList<>();
		
		for(int i = 0; i<8; i++){
			details.add(new Detail((Long)detailsCreate[i][0], (String)detailsCreate[i][1], (Integer)detailsCreate[i][2], (Double)detailsCreate[i][3], (Long)detailsCreate[i][4]));
		}
		
		when(purchaseRepository.findAll()).thenReturn(allPurchases);
		
		when(detailsRepository.findByPurchaseIdIn(any())).thenReturn(details);
		
		List<Purchase> purchasesNotExpired = purchaseService.getPurchasesNotExpired(date);
		
		verify(purchaseRepository, times(1)).findAll();
		verify(detailsRepository, times(1)).findByPurchaseIdIn(any());
		
		assertTrue(purchasesNotExpired.size() == 2);
		assertTrue(purchasesNotExpired.get(0).getId().equals(1L));
		assertTrue(purchasesNotExpired.get(0).getPurchaseDetails().size() == 2);
		assertTrue(purchasesNotExpired.get(0).getPurchaseDetails().get(0).getId().equals(10L));
		assertTrue(purchasesNotExpired.get(0).getPurchaseDetails().get(1).getId().equals(20L));
		assertTrue(purchasesNotExpired.get(1).getId().equals(3L));
		assertTrue(purchasesNotExpired.get(1).getPurchaseDetails().size() == 2);
		assertTrue(purchasesNotExpired.get(1).getPurchaseDetails().get(0).getId().equals(50L));
		assertTrue(purchasesNotExpired.get(1).getPurchaseDetails().get(1).getId().equals(60L));
		
		
	}
	
	@Test
	public void savePurchaseTest(){
		Purchase purchase = new Purchase();
		purchaseService.savePurchase(purchase);
		
		verify(purchaseRepository, times(1)).savePurchase(purchase);
	}
	
		

}
