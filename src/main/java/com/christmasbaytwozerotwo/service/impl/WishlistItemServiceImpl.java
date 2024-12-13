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
import com.christmasbaytwozerotwo.dao.WishlistItemDAO;
import com.christmasbaytwozerotwo.domain.WishlistItem;
import com.christmasbaytwozerotwo.dto.WishlistItemDTO;
import com.christmasbaytwozerotwo.dto.WishlistItemSearchDTO;
import com.christmasbaytwozerotwo.dto.WishlistItemPageDTO;
import com.christmasbaytwozerotwo.dto.WishlistItemConvertCriteriaDTO;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import com.christmasbaytwozerotwo.service.WishlistItemService;
import com.christmasbaytwozerotwo.util.ControllerUtils;





@Service
public class WishlistItemServiceImpl extends GenericServiceImpl<WishlistItem, Integer> implements WishlistItemService {

    private final static Logger logger = LoggerFactory.getLogger(WishlistItemServiceImpl.class);

	@Autowired
	WishlistItemDAO wishlistItemDao;

	


	@Override
	public GenericDAO<WishlistItem, Integer> getDAO() {
		return (GenericDAO<WishlistItem, Integer>) wishlistItemDao;
	}
	
	public List<WishlistItem> findAll () {
		List<WishlistItem> wishlistItems = wishlistItemDao.findAll();
		
		return wishlistItems;	
		
	}

	public ResultDTO addWishlistItem(WishlistItemDTO wishlistItemDTO, RequestDTO requestDTO) {

		WishlistItem wishlistItem = new WishlistItem();

		wishlistItem.setWishlistItemId(wishlistItemDTO.getWishlistItemId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		wishlistItem = wishlistItemDao.save(wishlistItem);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<WishlistItem> getAllWishlistItems(Pageable pageable) {
		return wishlistItemDao.findAll(pageable);
	}

	public Page<WishlistItem> getAllWishlistItems(Specification<WishlistItem> spec, Pageable pageable) {
		return wishlistItemDao.findAll(spec, pageable);
	}

	public ResponseEntity<WishlistItemPageDTO> getWishlistItems(WishlistItemSearchDTO wishlistItemSearchDTO) {
	
			Integer wishlistItemId = wishlistItemSearchDTO.getWishlistItemId(); 
 			String sortBy = wishlistItemSearchDTO.getSortBy();
			String sortOrder = wishlistItemSearchDTO.getSortOrder();
			String searchQuery = wishlistItemSearchDTO.getSearchQuery();
			Integer page = wishlistItemSearchDTO.getPage();
			Integer size = wishlistItemSearchDTO.getSize();

	        Specification<WishlistItem> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, wishlistItemId, "wishlistItemId"); 
			

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

		Page<WishlistItem> wishlistItems = this.getAllWishlistItems(spec, pageable);
		
		//System.out.println(String.valueOf(wishlistItems.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(wishlistItems.getTotalPages()));
		
		List<WishlistItem> wishlistItemsList = wishlistItems.getContent();
		
		WishlistItemConvertCriteriaDTO convertCriteria = new WishlistItemConvertCriteriaDTO();
		List<WishlistItemDTO> wishlistItemDTOs = this.convertWishlistItemsToWishlistItemDTOs(wishlistItemsList,convertCriteria);
		
		WishlistItemPageDTO wishlistItemPageDTO = new WishlistItemPageDTO();
		wishlistItemPageDTO.setWishlistItems(wishlistItemDTOs);
		wishlistItemPageDTO.setTotalElements(wishlistItems.getTotalElements());
		return ResponseEntity.ok(wishlistItemPageDTO);
	}

	public List<WishlistItemDTO> convertWishlistItemsToWishlistItemDTOs(List<WishlistItem> wishlistItems, WishlistItemConvertCriteriaDTO convertCriteria) {
		
		List<WishlistItemDTO> wishlistItemDTOs = new ArrayList<WishlistItemDTO>();
		
		for (WishlistItem wishlistItem : wishlistItems) {
			wishlistItemDTOs.add(convertWishlistItemToWishlistItemDTO(wishlistItem,convertCriteria));
		}
		
		return wishlistItemDTOs;

	}
	
	public WishlistItemDTO convertWishlistItemToWishlistItemDTO(WishlistItem wishlistItem, WishlistItemConvertCriteriaDTO convertCriteria) {
		
		WishlistItemDTO wishlistItemDTO = new WishlistItemDTO();
		
		wishlistItemDTO.setWishlistItemId(wishlistItem.getWishlistItemId());

	

		
		return wishlistItemDTO;
	}

	public ResultDTO updateWishlistItem(WishlistItemDTO wishlistItemDTO, RequestDTO requestDTO) {
		
		WishlistItem wishlistItem = wishlistItemDao.getById(wishlistItemDTO.getWishlistItemId());

		wishlistItem.setWishlistItemId(ControllerUtils.setValue(wishlistItem.getWishlistItemId(), wishlistItemDTO.getWishlistItemId()));



        wishlistItem = wishlistItemDao.save(wishlistItem);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public WishlistItemDTO getWishlistItemDTOById(Integer wishlistItemId) {
	
		WishlistItem wishlistItem = wishlistItemDao.getById(wishlistItemId);
			
		
		WishlistItemConvertCriteriaDTO convertCriteria = new WishlistItemConvertCriteriaDTO();
		return(this.convertWishlistItemToWishlistItemDTO(wishlistItem,convertCriteria));
	}







}
