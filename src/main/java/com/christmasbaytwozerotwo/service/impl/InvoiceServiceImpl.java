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
import com.christmasbaytwozerotwo.dao.InvoiceDAO;
import com.christmasbaytwozerotwo.domain.Invoice;
import com.christmasbaytwozerotwo.dto.InvoiceDTO;
import com.christmasbaytwozerotwo.dto.InvoiceSearchDTO;
import com.christmasbaytwozerotwo.dto.InvoicePageDTO;
import com.christmasbaytwozerotwo.dto.InvoiceConvertCriteriaDTO;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import com.christmasbaytwozerotwo.service.InvoiceService;
import com.christmasbaytwozerotwo.util.ControllerUtils;





@Service
public class InvoiceServiceImpl extends GenericServiceImpl<Invoice, Integer> implements InvoiceService {

    private final static Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

	@Autowired
	InvoiceDAO invoiceDao;

	


	@Override
	public GenericDAO<Invoice, Integer> getDAO() {
		return (GenericDAO<Invoice, Integer>) invoiceDao;
	}
	
	public List<Invoice> findAll () {
		List<Invoice> invoices = invoiceDao.findAll();
		
		return invoices;	
		
	}

	public ResultDTO addInvoice(InvoiceDTO invoiceDTO, RequestDTO requestDTO) {

		Invoice invoice = new Invoice();

		invoice.setInvoiceId(invoiceDTO.getInvoiceId());


		invoice.setIssueDate(invoiceDTO.getIssueDate());


		invoice.setTotalAmount(invoiceDTO.getTotalAmount());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		invoice = invoiceDao.save(invoice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Invoice> getAllInvoices(Pageable pageable) {
		return invoiceDao.findAll(pageable);
	}

	public Page<Invoice> getAllInvoices(Specification<Invoice> spec, Pageable pageable) {
		return invoiceDao.findAll(spec, pageable);
	}

	public ResponseEntity<InvoicePageDTO> getInvoices(InvoiceSearchDTO invoiceSearchDTO) {
	
			Integer invoiceId = invoiceSearchDTO.getInvoiceId(); 
    			String sortBy = invoiceSearchDTO.getSortBy();
			String sortOrder = invoiceSearchDTO.getSortOrder();
			String searchQuery = invoiceSearchDTO.getSearchQuery();
			Integer page = invoiceSearchDTO.getPage();
			Integer size = invoiceSearchDTO.getSize();

	        Specification<Invoice> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, invoiceId, "invoiceId"); 
			
 			
			

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

		Page<Invoice> invoices = this.getAllInvoices(spec, pageable);
		
		//System.out.println(String.valueOf(invoices.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(invoices.getTotalPages()));
		
		List<Invoice> invoicesList = invoices.getContent();
		
		InvoiceConvertCriteriaDTO convertCriteria = new InvoiceConvertCriteriaDTO();
		List<InvoiceDTO> invoiceDTOs = this.convertInvoicesToInvoiceDTOs(invoicesList,convertCriteria);
		
		InvoicePageDTO invoicePageDTO = new InvoicePageDTO();
		invoicePageDTO.setInvoices(invoiceDTOs);
		invoicePageDTO.setTotalElements(invoices.getTotalElements());
		return ResponseEntity.ok(invoicePageDTO);
	}

	public List<InvoiceDTO> convertInvoicesToInvoiceDTOs(List<Invoice> invoices, InvoiceConvertCriteriaDTO convertCriteria) {
		
		List<InvoiceDTO> invoiceDTOs = new ArrayList<InvoiceDTO>();
		
		for (Invoice invoice : invoices) {
			invoiceDTOs.add(convertInvoiceToInvoiceDTO(invoice,convertCriteria));
		}
		
		return invoiceDTOs;

	}
	
	public InvoiceDTO convertInvoiceToInvoiceDTO(Invoice invoice, InvoiceConvertCriteriaDTO convertCriteria) {
		
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		
		invoiceDTO.setInvoiceId(invoice.getInvoiceId());

	
		invoiceDTO.setIssueDate(invoice.getIssueDate());

	
		invoiceDTO.setTotalAmount(invoice.getTotalAmount());

	

		
		return invoiceDTO;
	}

	public ResultDTO updateInvoice(InvoiceDTO invoiceDTO, RequestDTO requestDTO) {
		
		Invoice invoice = invoiceDao.getById(invoiceDTO.getInvoiceId());

		invoice.setInvoiceId(ControllerUtils.setValue(invoice.getInvoiceId(), invoiceDTO.getInvoiceId()));

		invoice.setIssueDate(ControllerUtils.setValue(invoice.getIssueDate(), invoiceDTO.getIssueDate()));

		invoice.setTotalAmount(ControllerUtils.setValue(invoice.getTotalAmount(), invoiceDTO.getTotalAmount()));



        invoice = invoiceDao.save(invoice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public InvoiceDTO getInvoiceDTOById(Integer invoiceId) {
	
		Invoice invoice = invoiceDao.getById(invoiceId);
			
		
		InvoiceConvertCriteriaDTO convertCriteria = new InvoiceConvertCriteriaDTO();
		return(this.convertInvoiceToInvoiceDTO(invoice,convertCriteria));
	}







}
