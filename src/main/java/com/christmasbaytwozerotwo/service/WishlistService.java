package com.christmasbaytwozerotwo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.christmasbaytwozerotwo.domain.Wishlist;
import com.christmasbaytwozerotwo.dto.WishlistDTO;
import com.christmasbaytwozerotwo.dto.WishlistSearchDTO;
import com.christmasbaytwozerotwo.dto.WishlistPageDTO;
import com.christmasbaytwozerotwo.dto.WishlistConvertCriteriaDTO;
import com.christmasbaytwozerotwo.service.GenericService;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WishlistService extends GenericService<Wishlist, Integer> {

	List<Wishlist> findAll();

	ResultDTO addWishlist(WishlistDTO wishlistDTO, RequestDTO requestDTO);

	ResultDTO updateWishlist(WishlistDTO wishlistDTO, RequestDTO requestDTO);

    Page<Wishlist> getAllWishlists(Pageable pageable);

    Page<Wishlist> getAllWishlists(Specification<Wishlist> spec, Pageable pageable);

	ResponseEntity<WishlistPageDTO> getWishlists(WishlistSearchDTO wishlistSearchDTO);
	
	List<WishlistDTO> convertWishlistsToWishlistDTOs(List<Wishlist> wishlists, WishlistConvertCriteriaDTO convertCriteria);

	WishlistDTO getWishlistDTOById(Integer wishlistId);







}





