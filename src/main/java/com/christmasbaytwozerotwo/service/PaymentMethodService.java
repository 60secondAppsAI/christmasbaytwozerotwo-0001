package com.christmasbaytwozerotwo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.christmasbaytwozerotwo.domain.PaymentMethod;
import com.christmasbaytwozerotwo.dto.PaymentMethodDTO;
import com.christmasbaytwozerotwo.dto.PaymentMethodSearchDTO;
import com.christmasbaytwozerotwo.dto.PaymentMethodPageDTO;
import com.christmasbaytwozerotwo.dto.PaymentMethodConvertCriteriaDTO;
import com.christmasbaytwozerotwo.service.GenericService;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PaymentMethodService extends GenericService<PaymentMethod, Integer> {

	List<PaymentMethod> findAll();

	ResultDTO addPaymentMethod(PaymentMethodDTO paymentMethodDTO, RequestDTO requestDTO);

	ResultDTO updatePaymentMethod(PaymentMethodDTO paymentMethodDTO, RequestDTO requestDTO);

    Page<PaymentMethod> getAllPaymentMethods(Pageable pageable);

    Page<PaymentMethod> getAllPaymentMethods(Specification<PaymentMethod> spec, Pageable pageable);

	ResponseEntity<PaymentMethodPageDTO> getPaymentMethods(PaymentMethodSearchDTO paymentMethodSearchDTO);
	
	List<PaymentMethodDTO> convertPaymentMethodsToPaymentMethodDTOs(List<PaymentMethod> paymentMethods, PaymentMethodConvertCriteriaDTO convertCriteria);

	PaymentMethodDTO getPaymentMethodDTOById(Integer paymentMethodId);







}





