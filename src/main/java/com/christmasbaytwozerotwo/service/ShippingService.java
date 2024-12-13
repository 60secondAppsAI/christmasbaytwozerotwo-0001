package com.christmasbaytwozerotwo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.christmasbaytwozerotwo.domain.Shipping;
import com.christmasbaytwozerotwo.dto.ShippingDTO;
import com.christmasbaytwozerotwo.dto.ShippingSearchDTO;
import com.christmasbaytwozerotwo.dto.ShippingPageDTO;
import com.christmasbaytwozerotwo.dto.ShippingConvertCriteriaDTO;
import com.christmasbaytwozerotwo.service.GenericService;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShippingService extends GenericService<Shipping, Integer> {

	List<Shipping> findAll();

	ResultDTO addShipping(ShippingDTO shippingDTO, RequestDTO requestDTO);

	ResultDTO updateShipping(ShippingDTO shippingDTO, RequestDTO requestDTO);

    Page<Shipping> getAllShippings(Pageable pageable);

    Page<Shipping> getAllShippings(Specification<Shipping> spec, Pageable pageable);

	ResponseEntity<ShippingPageDTO> getShippings(ShippingSearchDTO shippingSearchDTO);
	
	List<ShippingDTO> convertShippingsToShippingDTOs(List<Shipping> shippings, ShippingConvertCriteriaDTO convertCriteria);

	ShippingDTO getShippingDTOById(Integer shippingId);







}





