package com.stklog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stklog.domain.model.Client;
import com.stklog.domain.model.Delivery;
import com.stklog.domain.model.DeliveryStatus;
import com.stklog.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RequestDeliveryService {

	private BookClientService bookClientService;
	private DeliveryRepository deliveryRepository;
	
	@Transactional
	public Delivery request(Delivery delivery) {
		Client client = bookClientService.search(delivery.getClient().getId());
		
		delivery.setClient(client);
		delivery.setStatus(DeliveryStatus.PENDING);
		delivery.setRequestDate(OffsetDateTime.now());
		
		return deliveryRepository.save(delivery);
	}
}
