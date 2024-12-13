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
import com.christmasbaytwozerotwo.dao.MessageDAO;
import com.christmasbaytwozerotwo.domain.Message;
import com.christmasbaytwozerotwo.dto.MessageDTO;
import com.christmasbaytwozerotwo.dto.MessageSearchDTO;
import com.christmasbaytwozerotwo.dto.MessagePageDTO;
import com.christmasbaytwozerotwo.dto.MessageConvertCriteriaDTO;
import com.christmasbaytwozerotwo.dto.common.RequestDTO;
import com.christmasbaytwozerotwo.dto.common.ResultDTO;
import com.christmasbaytwozerotwo.service.MessageService;
import com.christmasbaytwozerotwo.util.ControllerUtils;





@Service
public class MessageServiceImpl extends GenericServiceImpl<Message, Integer> implements MessageService {

    private final static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	MessageDAO messageDao;

	


	@Override
	public GenericDAO<Message, Integer> getDAO() {
		return (GenericDAO<Message, Integer>) messageDao;
	}
	
	public List<Message> findAll () {
		List<Message> messages = messageDao.findAll();
		
		return messages;	
		
	}

	public ResultDTO addMessage(MessageDTO messageDTO, RequestDTO requestDTO) {

		Message message = new Message();

		message.setMessageId(messageDTO.getMessageId());


		message.setContent(messageDTO.getContent());


		message.setSentDate(messageDTO.getSentDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		message = messageDao.save(message);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Message> getAllMessages(Pageable pageable) {
		return messageDao.findAll(pageable);
	}

	public Page<Message> getAllMessages(Specification<Message> spec, Pageable pageable) {
		return messageDao.findAll(spec, pageable);
	}

	public ResponseEntity<MessagePageDTO> getMessages(MessageSearchDTO messageSearchDTO) {
	
			Integer messageId = messageSearchDTO.getMessageId(); 
 			String content = messageSearchDTO.getContent(); 
   			String sortBy = messageSearchDTO.getSortBy();
			String sortOrder = messageSearchDTO.getSortOrder();
			String searchQuery = messageSearchDTO.getSearchQuery();
			Integer page = messageSearchDTO.getPage();
			Integer size = messageSearchDTO.getSize();

	        Specification<Message> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, messageId, "messageId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, content, "content"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("content")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Message> messages = this.getAllMessages(spec, pageable);
		
		//System.out.println(String.valueOf(messages.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(messages.getTotalPages()));
		
		List<Message> messagesList = messages.getContent();
		
		MessageConvertCriteriaDTO convertCriteria = new MessageConvertCriteriaDTO();
		List<MessageDTO> messageDTOs = this.convertMessagesToMessageDTOs(messagesList,convertCriteria);
		
		MessagePageDTO messagePageDTO = new MessagePageDTO();
		messagePageDTO.setMessages(messageDTOs);
		messagePageDTO.setTotalElements(messages.getTotalElements());
		return ResponseEntity.ok(messagePageDTO);
	}

	public List<MessageDTO> convertMessagesToMessageDTOs(List<Message> messages, MessageConvertCriteriaDTO convertCriteria) {
		
		List<MessageDTO> messageDTOs = new ArrayList<MessageDTO>();
		
		for (Message message : messages) {
			messageDTOs.add(convertMessageToMessageDTO(message,convertCriteria));
		}
		
		return messageDTOs;

	}
	
	public MessageDTO convertMessageToMessageDTO(Message message, MessageConvertCriteriaDTO convertCriteria) {
		
		MessageDTO messageDTO = new MessageDTO();
		
		messageDTO.setMessageId(message.getMessageId());

	
		messageDTO.setContent(message.getContent());

	
		messageDTO.setSentDate(message.getSentDate());

	

		
		return messageDTO;
	}

	public ResultDTO updateMessage(MessageDTO messageDTO, RequestDTO requestDTO) {
		
		Message message = messageDao.getById(messageDTO.getMessageId());

		message.setMessageId(ControllerUtils.setValue(message.getMessageId(), messageDTO.getMessageId()));

		message.setContent(ControllerUtils.setValue(message.getContent(), messageDTO.getContent()));

		message.setSentDate(ControllerUtils.setValue(message.getSentDate(), messageDTO.getSentDate()));



        message = messageDao.save(message);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MessageDTO getMessageDTOById(Integer messageId) {
	
		Message message = messageDao.getById(messageId);
			
		
		MessageConvertCriteriaDTO convertCriteria = new MessageConvertCriteriaDTO();
		return(this.convertMessageToMessageDTO(message,convertCriteria));
	}







}
