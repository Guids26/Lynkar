package com.br.lynkar.Lynkar.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.br.lynkar.Lynkar.model.LinkVisitEntity;
import com.br.lynkar.Lynkar.repository.LinkVisitRepository;

@Service
public class LinkVisitService {
	
	@Autowired
	private LinkVisitRepository repository;
	
	@Async
	public void registerVisit(UUID linkId) {
		
		LinkVisitEntity entity =  new LinkVisitEntity();
		
		entity.setCreatedAt(LocalDateTime.now());
		entity.setLinkId(linkId);
		
		repository.save(entity);
		
	}

}
