package com.tiagopinto.blip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagopinto.blip.model.Detail;

public interface DetailsRepository extends JpaRepository<Detail, Long>{
	
	List<Detail> findByPurchaseId(Long purchaseId);
	List<Detail> findByPurchaseIdIn(List<Long> purchaseIds);

}
//Details(id:Long, description:String, quantity:Integer, value:Double)