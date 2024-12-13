package com.christmasbaytwozerotwo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.christmasbaytwozerotwo.domain.Invoice;
import com.christmasbaytwozerotwo.dto.InvoiceDTO;
import com.christmasbaytwozerotwo.dto.InvoiceSearchDTO;
import com.christmasbaytwozerotwo.dto.InvoicePageDTO;
import com.christmasbaytwozerotwo.dto.InvoiceConvertCriteriaDTO;
import com.christmasbaytwozerotwo.service.GenericService;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InvoiceService extends GenericService<Invoice, Integer> {

	List<Invoice> findAll();

	ResultDTO addInvoice(InvoiceDTO invoiceDTO, RequestDTO requestDTO);

	ResultDTO updateInvoice(InvoiceDTO invoiceDTO, RequestDTO requestDTO);

    Page<Invoice> getAllInvoices(Pageable pageable);

    Page<Invoice> getAllInvoices(Specification<Invoice> spec, Pageable pageable);

	ResponseEntity<InvoicePageDTO> getInvoices(InvoiceSearchDTO invoiceSearchDTO);
	
	List<InvoiceDTO> convertInvoicesToInvoiceDTOs(List<Invoice> invoices, InvoiceConvertCriteriaDTO convertCriteria);

	InvoiceDTO getInvoiceDTOById(Integer invoiceId);







}





