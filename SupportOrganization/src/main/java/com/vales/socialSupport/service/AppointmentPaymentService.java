package com.vales.socialSupport.service;


import com.vales.socialSupport.repository.AppointmentPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppointmentPaymentService extends ParentService {

	@Autowired
	private AppointmentPaymentRepository appointmentPaymentRepository;

	@Override
	protected void initializationRepository() {
		setParentService(appointmentPaymentRepository);
	}

}
