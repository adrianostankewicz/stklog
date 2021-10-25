package com.stklog.domain.service;

import org.springframework.stereotype.Service;

import com.stklog.domain.exception.EntityNotFoundException;
import com.stklog.domain.model.Delivery;
import com.stklog.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SearchDeliveryService {

	private DeliveryRepository deliveryRepository;
	
	public Delivery search(Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
	}
}
