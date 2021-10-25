package com.stklog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stklog.domain.exception.BusinessException;
import com.stklog.domain.model.Client;
import com.stklog.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookClientService {

	private ClientRepository clientRepository;
	
	public Client search(Long clientId) {
		return clientRepository.findById(clientId)
				.orElseThrow(() -> new BusinessException("Cliente não encontrado"));
	}
	
	@Transactional
	public Client save(Client client) {
		boolean emailInUse = clientRepository.findByEmail(client.getEmail())
				.stream()
				.anyMatch(clientExist -> !clientExist.equals(client));
		
		if(emailInUse) {
			throw new BusinessException("Já existe um cliente cadastrado com esse e-mail");
		}
		
		return clientRepository.save(client);
	}
	
	public void remove(Long clientId) {
		clientRepository.deleteById(clientId);
	}
}
