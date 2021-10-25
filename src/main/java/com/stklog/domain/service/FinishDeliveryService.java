package com.stklog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stklog.domain.model.Delivery;
import com.stklog.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinishDeliveryService {
	
	private DeliveryRepository deliveryRepository;
	private SearchDeliveryService searchDeliveryService;

	@Transactional
	public void finish(Long deliveryId) {
		Delivery delivery = searchDeliveryService.search(deliveryId);
		
		delivery.finish();
		deliveryRepository.save(delivery);
	}
}
