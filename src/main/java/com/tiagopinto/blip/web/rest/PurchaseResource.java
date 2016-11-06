package com.tiagopinto.blip.web.rest;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.tiagopinto.blip.model.Purchase;
import com.tiagopinto.blip.service.PurchasesService;

@RestController
@RequestMapping("/PurchasesWebService")
public class PurchaseResource {
	
	@Inject
	private PurchasesService purchaseService;
	
	@GetMapping("/PurchasesNotExpired")
	@Timed
	@Counted
	@Metered
	public List<Purchase> getPurchases(@RequestAttribute DateTime date){
		return purchaseService.getPurchasesNotExpired(date);
	}
	
	@GetMapping("/SavePurchase")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Timed
	@Counted
	@ExceptionMetered
	public void savePurchase(@RequestAttribute Purchase purchase){
		purchaseService.savePurchase(purchase);
	}
}
