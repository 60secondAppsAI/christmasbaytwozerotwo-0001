package com.christmasbaytwozerotwo.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.christmasbaytwozerotwo.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.christmasbaytwozerotwo.domain.Address;
import com.christmasbaytwozerotwo.dto.AddressDTO;
import com.christmasbaytwozerotwo.dto.AddressSearchDTO;
import com.christmasbaytwozerotwo.dto.AddressPageDTO;
import com.christmasbaytwozerotwo.service.AddressService;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/address")
@RestController
public class AddressController {

	private final static Logger logger = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	AddressService addressService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Address> getAll() {

		List<Address> addresss = addressService.findAll();
		
		return addresss;	
	}

	@GetMapping(value = "/{addressId}")
	@ResponseBody
	public AddressDTO getAddress(@PathVariable Integer addressId) {
		
		return (addressService.getAddressDTOById(addressId));
	}

 	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = addressService.addAddress(addressDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/addresss")
	public ResponseEntity<AddressPageDTO> getAddresss(AddressSearchDTO addressSearchDTO) {
 
		return addressService.getAddresss(addressSearchDTO);
	}	

	@RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
	public ResponseEntity<?> updateAddress(@RequestBody AddressDTO addressDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = addressService.updateAddress(addressDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
