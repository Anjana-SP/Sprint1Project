package com.edusystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.edusystem.entity.Payment;
import com.edusystem.exception.PaymentNotFoundException;
import com.edusystem.repository.PaymentRepository;
import com.edusystem.service.PaymentService;
import com.edusystem.service.PaymentServiceImpl;

@SpringBootTest
public class PaymentServiceTest {

	@InjectMocks
	private PaymentService paymentService = new PaymentServiceImpl();

	@Mock
	private PaymentRepository paymentRepository;

	@Test
	public void testPaymentById() {

		Payment payment = new Payment();
		payment.setPaymentId(200);
		payment.setModeOfPayment("cash");
		payment.setPaymentAmount(1600.00);
		payment.setStatus("paid");
		payment.setDate(LocalDate.now());

		Optional<Payment> optionalPayment = Optional.of(payment);

		when(paymentRepository.findById(200)).thenReturn(optionalPayment);

		Payment myPayment = paymentService.getPaymentById(200);

		assertEquals(1600.00, myPayment.getPaymentAmount());
	}

	@Test
	public void testGetPaymentByIdWithException() {

		when(paymentRepository.findById(100)).thenThrow(PaymentNotFoundException.class);

		assertThrows(PaymentNotFoundException.class, () -> paymentService.getPaymentById(100));

	}

	@Test
	public void testGetAllPayment() {
		Payment payment = new Payment();
		payment.setPaymentId(200);
		payment.setModeOfPayment("cash");
		payment.setPaymentAmount(1600.00);
		payment.setStatus("paid");
		payment.setDate(LocalDate.now());

		Payment payment1 = new Payment();
		payment1.setPaymentId(201);
		payment1.setModeOfPayment("UPI");
		payment1.setPaymentAmount(1700.00);
		payment1.setStatus("paid");
		payment1.setDate(LocalDate.now());

		Payment payment2 = new Payment();
		payment2.setPaymentId(202);
		payment2.setModeOfPayment("dd");
		payment2.setPaymentAmount(1600.00);
		payment2.setStatus("pending");
		payment2.setDate(LocalDate.now());

		List<Payment> paymentList = new ArrayList<>();
		paymentList.add(payment);
		paymentList.add(payment1);
		paymentList.add(payment2);

		when(paymentRepository.findAll()).thenReturn(paymentList);

		List<Payment> payments = paymentService.getAllPayment();

		assertEquals(3, payments.size());

	}

	@Test
	public void testSavePayment() {

		Payment payment = new Payment();
		payment.setPaymentId(200);
		payment.setModeOfPayment("cash");
		payment.setPaymentAmount(1600.00);
		payment.setStatus("paid");
		payment.setDate(LocalDate.now());

		when(paymentRepository.save(payment)).thenReturn(payment);

		Payment newPayment = paymentService.savePayment(payment);

		assertEquals("cash", newPayment.getModeOfPayment());

		verify(paymentRepository, times(1)).save(payment);

	}

	@Test
	public void testDeletePayment() {
		
		Payment payment = new Payment();
		payment.setPaymentId(200);
		payment.setModeOfPayment("cash");
		payment.setPaymentAmount(1600.00);
		payment.setStatus("paid");
		payment.setDate(LocalDate.now());
		
		Optional<Payment> optionalPayment = Optional.of(payment);
		
		when(paymentRepository.findById(100)).thenReturn(optionalPayment);
		
         paymentService.deletePayment(100);
		
		verify(paymentRepository,times(1)).findById(100);
		verify(paymentRepository,times(1)).deleteById(100);
	
	}
	@Test
	void testUpdateCourse() {

		Payment payment = new Payment();
		payment.setPaymentId(200);
		payment.setModeOfPayment("cash");
		payment.setPaymentAmount(1600.00);
		payment.setStatus("paid");
		payment.setDate(LocalDate.now());

		Optional<Payment> optionalPayment = Optional.of(payment);

		when(paymentRepository.findById(200)).thenReturn(optionalPayment);

		paymentService.updatePayment(payment);

		verify(paymentRepository, times(1)).findById(200);
		verify(paymentRepository, times(1)).save(payment);

	}
}