package com.stklog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.stklog.domain.model.DeliveryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryModel {

	private Long id;
	private ClientResumeModel client;
	private RecepientModel recipient;
	private BigDecimal rate;
	private DeliveryStatus status;
	private OffsetDateTime requestDate;
	private OffsetDateTime finishedDate;
}
