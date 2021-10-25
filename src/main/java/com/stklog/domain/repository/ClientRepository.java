package com.stklog.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stklog.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	Optional<Client> findByEmail(String email);
}
