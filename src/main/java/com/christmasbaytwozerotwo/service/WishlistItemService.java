package com.christmasbaytwozerotwo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.christmasbaytwozerotwo.domain.WishlistItem;
import com.christmasbaytwozerotwo.dto.WishlistItemDTO;
import com.christmasbaytwozerotwo.dto.WishlistItemSearchDTO;
import com.christmasbaytwozerotwo.dto.WishlistItemPageDTO;
import com.christmasbaytwozerotwo.dto.WishlistItemConvertCriteriaDTO;
import com.christmasbaytwozerotwo.service.GenericService;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WishlistItemService extends GenericService<WishlistItem, Integer> {

	List<WishlistItem> findAll();

	ResultDTO addWishlistItem(WishlistItemDTO wishlistItemDTO, RequestDTO requestDTO);

	ResultDTO updateWishlistItem(WishlistItemDTO wishlistItemDTO, RequestDTO requestDTO);

    Page<WishlistItem> getAllWishlistItems(Pageable pageable);

    Page<WishlistItem> getAllWishlistItems(Specification<WishlistItem> spec, Pageable pageable);

	ResponseEntity<WishlistItemPageDTO> getWishlistItems(WishlistItemSearchDTO wishlistItemSearchDTO);
	
	List<WishlistItemDTO> convertWishlistItemsToWishlistItemDTOs(List<WishlistItem> wishlistItems, WishlistItemConvertCriteriaDTO convertCriteria);

	WishlistItemDTO getWishlistItemDTOById(Integer wishlistItemId);







}





