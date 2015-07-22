package com.vales.socialSupport.service;


import com.vales.socialSupport.entity.AppointmentPayment;
import com.vales.socialSupport.repository.AppointmentPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AppointmentPaymentService {

	@Autowired
	private AppointmentPaymentRepository appointmentPaymentRepository;

	public List<AppointmentPayment> findAll() {
        return appointmentPaymentRepository.findAll();
	}

	public void save(AppointmentPayment appointmentPayment) {
        appointmentPaymentRepository.save(appointmentPayment);
	}

	public void remove(AppointmentPayment appointmentPayment) {
        appointmentPaymentRepository.delete(appointmentPayment);
	}

}
