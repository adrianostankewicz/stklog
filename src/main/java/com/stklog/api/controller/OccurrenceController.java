package com.stklog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stklog.api.assembler.OccurrenceAssembler;
import com.stklog.api.model.OccurrenceModel;
import com.stklog.api.model.input.OccurrenceInput;
import com.stklog.domain.model.Delivery;
import com.stklog.domain.model.Occurrence;
import com.stklog.domain.service.RegisterOccurrenceService;
import com.stklog.domain.service.SearchDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
public class OccurrenceController {

	private SearchDeliveryService searchDeliveryService;
	private RegisterOccurrenceService registerOccurrenceService;
	private OccurrenceAssembler occurrenceAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OccurrenceModel register(@PathVariable Long deliveryId,
		@Valid @RequestBody OccurrenceInput occurrenceInput) {
		
		Occurrence registeredOccurrence = registerOccurrenceService.
				register(deliveryId, occurrenceInput.getDescription());
		
		return occurrenceAssembler.toModel(registeredOccurrence);
	}
	
	public List<OccurrenceModel> list(@PathVariable Long deliveryId){
		Delivery delivery = searchDeliveryService.search(deliveryId);
		
		return occurrenceAssembler.toCollectionModel(delivery.getOccurrences());
	}
}
