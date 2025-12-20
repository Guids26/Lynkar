package com.br.lynkar.Lynkar.service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lynkar.Lynkar.config.exception.LynkarBusinessException;
import com.br.lynkar.Lynkar.dto.link.LinkCreateDTO;
import com.br.lynkar.Lynkar.dto.link.LinkEditDTO;
import com.br.lynkar.Lynkar.dto.link.LinkResponseDTO;
import com.br.lynkar.Lynkar.mapper.LinkMapper;
import com.br.lynkar.Lynkar.model.LinkEntity;
import com.br.lynkar.Lynkar.repository.LinkRepository;

@Service
public class LinkService {

	private static final String ALLOWED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	@Autowired
	private LinkRepository repository;

	@Autowired
	private LinkMapper linkMapper;
	
	@Autowired
	private LinkVisitService linkVisitService;

	public LinkResponseDTO update(LinkEditDTO dto) {
		LinkEntity link = repository.findById(dto.getId())
				.orElseThrow(() -> new LynkarBusinessException("O link informado não existe."));

		if (dto.getCustomCode() != null && validateCode(dto.getCustomCode())) {
			link.setCode(dto.getCustomCode());
		}

		link.setId(dto.getId());
		link.setActive(dto.isFlActive());
		link.setUpdatedAt(LocalDateTime.now());
		link.setRedirectTo(dto.getLink());
		link = repository.save(link);

		return linkMapper.toDTO(link);
	}

	public LinkResponseDTO create(LinkCreateDTO dto, UUID createdBy) {
		LinkEntity newShortLink = new LinkEntity();
		newShortLink.setActive(true);
		LocalDateTime now = LocalDateTime.now();
		newShortLink.setCreatedAt(now);
		newShortLink.setUpdatedAt(now);
		newShortLink.setRedirectTo(dto.getLink());

		if (dto.getCustomCode() != null && !dto.getCustomCode().isBlank()) {

			if (!validateCode(dto.getCustomCode())) {
				throw new LynkarBusinessException("O codigo informado já está em uso.");
			}

			newShortLink.setCode(dto.getCustomCode());
		} else {
			newShortLink.setCode(generateNewValidCode());
		}

		newShortLink.setCreatedBy(createdBy);
		newShortLink = repository.save(newShortLink);
		return linkMapper.toDTO(newShortLink);
	}
	
	public LinkResponseDTO findByCode(String code) {
		LinkEntity link = repository.findFirstByCode(code)
				.orElseThrow(() -> new LynkarBusinessException("O codigo informado não existe."));
		return linkMapper.toDTO(link);
	}
	
	public LinkResponseDTO visit(String code) {
		LinkResponseDTO response = findByCode(code);
		linkVisitService.registerVisit(response.getId());
		return response;
	}

	private String generateNewValidCode() {
		String code = "";

		while (code.isBlank()) {
			String codeGenerated = generateRandomCode();
			if (!repository.existsByCode(codeGenerated)) {
				code = codeGenerated;
			}
		}
		return code;
	}

	private String generateRandomCode() {
		int minLength = 7;
		int maxLength = 10;
		Random random = new Random();

		int length = random.nextInt(minLength, maxLength);

		String code = "";

		for (int i = 0; i < length; i++) {
			int positionSelected = random.nextInt(ALLOWED.length());
			code += ALLOWED.charAt(positionSelected);
		}

		return code;
	}

	private boolean validateCode(String code) {

		if (code == null || code.length() < 7 || code.length() > 10) {
			throw new LynkarBusinessException("O codigo personalizado deve estar entre 7 e 10 caracteres.");
		}
		return !repository.existsByCode(code);
	}

}
