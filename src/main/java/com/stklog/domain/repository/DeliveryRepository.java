package com.stklog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stklog.domain.model.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

}
