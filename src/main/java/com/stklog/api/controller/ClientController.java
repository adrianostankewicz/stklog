package com.stklog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stklog.domain.model.Client;
import com.stklog.domain.repository.ClientRepository;
import com.stklog.domain.service.BookClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
	
	private ClientRepository clientRepository;
	private BookClientService clientService;
	
	@GetMapping
	public List<Client> listAll() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> find(@PathVariable Long atletaId) {
		return clientRepository.findById(atletaId)
				.map(client -> ResponseEntity.ok(client))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@Valid @RequestBody Client client) {
		return clientService.save(client);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Client> update(@PathVariable Long clientId,
			@Valid @RequestBody Client client){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(clientId);
		client = clientService.save(client);
		
		return ResponseEntity.ok(client);
	}
	
	@DeleteMapping("/clienteId")
	public ResponseEntity<Void> remove(@PathVariable Long clientId){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		clientService.remove(clientId);
		
		return ResponseEntity.noContent().build();
	}

}
