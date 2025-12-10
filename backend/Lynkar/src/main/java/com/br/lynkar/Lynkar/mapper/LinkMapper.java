package com.br.lynkar.Lynkar.mapper;

import org.mapstruct.Mapper;

import com.br.lynkar.Lynkar.dto.link.LinkResponseDTO;
import com.br.lynkar.Lynkar.model.LinkEntity;

@Mapper(componentModel = "spring")
public interface LinkMapper {
	
	LinkResponseDTO toDTO(LinkEntity linkEntity);

}
