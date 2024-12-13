package com.christmasbaytwozerotwo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SellerPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<SellerDTO> sellers;
}





