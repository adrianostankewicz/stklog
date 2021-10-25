package com.stklog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stklog.api.assembler.DeliveryAssembler;
import com.stklog.api.model.DeliveryModel;
import com.stklog.api.model.input.DeliveryInput;
import com.stklog.domain.model.Delivery;
import com.stklog.domain.repository.DeliveryRepository;
import com.stklog.domain.service.FinishDeliveryService;
import com.stklog.domain.service.RequestDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

	private DeliveryRepository deliveryRepository;
	private RequestDeliveryService requestDeliveryService;
	private FinishDeliveryService finishDeliveryService;
	private DeliveryAssembler deliveryAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryModel request(@Valid @RequestBody DeliveryInput deliveryInput) {
		Delivery newDelivery = deliveryAssembler.toEntity(deliveryInput);
		Delivery requestDelivery = requestDeliveryService.request(newDelivery);
		
		return deliveryAssembler.toModel(requestDelivery);
	}
	
	@PutMapping("/{deliveryId}/finish")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finish(@PathVariable Long deliveryId) {
		finishDeliveryService.finish(deliveryId);
	}

	@GetMapping
	public List<DeliveryModel> listAll() {
		return deliveryAssembler.toCollectionModel(deliveryRepository.findAll());
	}
	
	@GetMapping("/{deliveryId}")
	public ResponseEntity<DeliveryModel> search(@PathVariable Long deliveryId){
		return deliveryRepository.findById(deliveryId)
				.map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery)))
				.orElse(ResponseEntity.notFound().build());
	}
}
