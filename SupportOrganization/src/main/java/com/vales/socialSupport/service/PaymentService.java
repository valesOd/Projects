package com.vales.socialSupport.service;


import com.vales.socialSupport.repository.PaymentRepository;
import com.vales.socialSupport.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService extends ParentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	protected void initializationRepository() {
		setParentService(paymentRepository);
	}
}
