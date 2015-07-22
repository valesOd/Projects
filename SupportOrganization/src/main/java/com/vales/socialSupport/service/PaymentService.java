package com.vales.socialSupport.service;


import com.vales.socialSupport.repository.PaymentRepository;
import com.vales.socialSupport.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public List<Payment> findAll() {
        return paymentRepository.findAll();
	}

	public void save(Payment payment) {
		paymentRepository.save(payment);
	}

	public void remove(Payment payment) {
		paymentRepository.delete(payment);
	}

}
