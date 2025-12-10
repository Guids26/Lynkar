package com.br.lynkar.Lynkar.service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lynkar.Lynkar.dto.link.LinkCreateDTO;
import com.br.lynkar.Lynkar.dto.link.LinkResponseDTO;
import com.br.lynkar.Lynkar.mapper.LinkMapper;
import com.br.lynkar.Lynkar.model.LinkEntity;
import com.br.lynkar.Lynkar.repository.LinkRepository;

@Service
public class LinkService {
	
	private static final String ALLOWED =
	        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	@Autowired
	private LinkRepository repository;
	
	@Autowired
	private LinkMapper linkMapper;
	
	public LinkResponseDTO create(LinkCreateDTO dto, UUID createdBy) {
		LinkEntity newShortLink = new LinkEntity();
		newShortLink.setActive(true);
		LocalDateTime now = LocalDateTime.now();
		newShortLink.setCreatedAt(now);
		newShortLink.setUpdatedAt(now);
		newShortLink.setRedirectTo(dto.getLink());
		
		boolean generateNewCode = true;
		
		while(generateNewCode) {
			String code = generateRandomCode();
			if(!repository.existsByCode(code)) {
				newShortLink.setCode(code);
				generateNewCode = false;
			}
		}
		
		newShortLink.setCreatedBy(createdBy);
		newShortLink = repository.save(newShortLink);
		return linkMapper.toDTO(newShortLink);
	}
	
	private String generateRandomCode() {
		int minLength = 7;
		int maxLength = 10;
		Random random = new Random();
		
		int length = random.nextInt(minLength, maxLength);
		
		String code = "";
		
		for(int i =0 ; i < length ; i ++) {
			int positionSelected = random.nextInt(ALLOWED.length());
			code +=ALLOWED.charAt(positionSelected);
		}
		
		return code;
	}

}
