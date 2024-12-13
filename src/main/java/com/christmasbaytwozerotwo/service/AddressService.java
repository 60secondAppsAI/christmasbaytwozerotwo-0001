package com.christmasbaytwozerotwo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.christmasbaytwozerotwo.domain.Address;
import com.christmasbaytwozerotwo.dto.AddressDTO;
import com.christmasbaytwozerotwo.dto.AddressSearchDTO;
import com.christmasbaytwozerotwo.dto.AddressPageDTO;
import com.christmasbaytwozerotwo.dto.AddressConvertCriteriaDTO;
import com.christmasbaytwozerotwo.service.GenericService;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AddressService extends GenericService<Address, Integer> {

	List<Address> findAll();

	ResultDTO addAddress(AddressDTO addressDTO, RequestDTO requestDTO);

	ResultDTO updateAddress(AddressDTO addressDTO, RequestDTO requestDTO);

    Page<Address> getAllAddresss(Pageable pageable);

    Page<Address> getAllAddresss(Specification<Address> spec, Pageable pageable);

	ResponseEntity<AddressPageDTO> getAddresss(AddressSearchDTO addressSearchDTO);
	
	List<AddressDTO> convertAddresssToAddressDTOs(List<Address> addresss, AddressConvertCriteriaDTO convertCriteria);

	AddressDTO getAddressDTOById(Integer addressId);







}





