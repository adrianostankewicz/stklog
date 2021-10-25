package com.stklog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stklog.domain.model.Delivery;
import com.stklog.domain.model.Occurrence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegisterOccurrenceService {

	private SearchDeliveryService searchDeliveryService;
	
	@Transactional
	public Occurrence register(Long deliveryId, String description) {
		Delivery delivery = searchDeliveryService.search(deliveryId);
		
		return delivery.addOccurrence(description);
	}
	
}
