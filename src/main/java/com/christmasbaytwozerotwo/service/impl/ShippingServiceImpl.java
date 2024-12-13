package com.christmasbaytwozerotwo.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.service.GenericService;
import com.christmasbaytwozerotwo.service.impl.GenericServiceImpl;
import com.christmasbaytwozerotwo.dao.ShippingDAO;
import com.christmasbaytwozerotwo.domain.Shipping;
import com.christmasbaytwozerotwo.dto.ShippingDTO;
import com.christmasbaytwozerotwo.dto.ShippingSearchDTO;
import com.christmasbaytwozerotwo.dto.ShippingPageDTO;
import com.christmasbaytwozerotwo.dto.ShippingConvertCriteriaDTO;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import com.christmasbaytwozerotwo.service.ShippingService;
import com.christmasbaytwozerotwo.util.ControllerUtils;





@Service
public class ShippingServiceImpl extends GenericServiceImpl<Shipping, Integer> implements ShippingService {

    private final static Logger logger = LoggerFactory.getLogger(ShippingServiceImpl.class);

	@Autowired
	ShippingDAO shippingDao;

	


	@Override
	public GenericDAO<Shipping, Integer> getDAO() {
		return (GenericDAO<Shipping, Integer>) shippingDao;
	}
	
	public List<Shipping> findAll () {
		List<Shipping> shippings = shippingDao.findAll();
		
		return shippings;	
		
	}

	public ResultDTO addShipping(ShippingDTO shippingDTO, RequestDTO requestDTO) {

		Shipping shipping = new Shipping();

		shipping.setShippingId(shippingDTO.getShippingId());


		shipping.setEstimatedDeliveryDate(shippingDTO.getEstimatedDeliveryDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		shipping = shippingDao.save(shipping);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Shipping> getAllShippings(Pageable pageable) {
		return shippingDao.findAll(pageable);
	}

	public Page<Shipping> getAllShippings(Specification<Shipping> spec, Pageable pageable) {
		return shippingDao.findAll(spec, pageable);
	}

	public ResponseEntity<ShippingPageDTO> getShippings(ShippingSearchDTO shippingSearchDTO) {
	
			Integer shippingId = shippingSearchDTO.getShippingId(); 
   			String sortBy = shippingSearchDTO.getSortBy();
			String sortOrder = shippingSearchDTO.getSortOrder();
			String searchQuery = shippingSearchDTO.getSearchQuery();
			Integer page = shippingSearchDTO.getPage();
			Integer size = shippingSearchDTO.getSize();

	        Specification<Shipping> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, shippingId, "shippingId"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Shipping> shippings = this.getAllShippings(spec, pageable);
		
		//System.out.println(String.valueOf(shippings.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(shippings.getTotalPages()));
		
		List<Shipping> shippingsList = shippings.getContent();
		
		ShippingConvertCriteriaDTO convertCriteria = new ShippingConvertCriteriaDTO();
		List<ShippingDTO> shippingDTOs = this.convertShippingsToShippingDTOs(shippingsList,convertCriteria);
		
		ShippingPageDTO shippingPageDTO = new ShippingPageDTO();
		shippingPageDTO.setShippings(shippingDTOs);
		shippingPageDTO.setTotalElements(shippings.getTotalElements());
		return ResponseEntity.ok(shippingPageDTO);
	}

	public List<ShippingDTO> convertShippingsToShippingDTOs(List<Shipping> shippings, ShippingConvertCriteriaDTO convertCriteria) {
		
		List<ShippingDTO> shippingDTOs = new ArrayList<ShippingDTO>();
		
		for (Shipping shipping : shippings) {
			shippingDTOs.add(convertShippingToShippingDTO(shipping,convertCriteria));
		}
		
		return shippingDTOs;

	}
	
	public ShippingDTO convertShippingToShippingDTO(Shipping shipping, ShippingConvertCriteriaDTO convertCriteria) {
		
		ShippingDTO shippingDTO = new ShippingDTO();
		
		shippingDTO.setShippingId(shipping.getShippingId());

	
		shippingDTO.setEstimatedDeliveryDate(shipping.getEstimatedDeliveryDate());

	

		
		return shippingDTO;
	}

	public ResultDTO updateShipping(ShippingDTO shippingDTO, RequestDTO requestDTO) {
		
		Shipping shipping = shippingDao.getById(shippingDTO.getShippingId());

		shipping.setShippingId(ControllerUtils.setValue(shipping.getShippingId(), shippingDTO.getShippingId()));

		shipping.setEstimatedDeliveryDate(ControllerUtils.setValue(shipping.getEstimatedDeliveryDate(), shippingDTO.getEstimatedDeliveryDate()));



        shipping = shippingDao.save(shipping);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ShippingDTO getShippingDTOById(Integer shippingId) {
	
		Shipping shipping = shippingDao.getById(shippingId);
			
		
		ShippingConvertCriteriaDTO convertCriteria = new ShippingConvertCriteriaDTO();
		return(this.convertShippingToShippingDTO(shipping,convertCriteria));
	}







}
