package com.br.lynkar.Lynkar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.br.lynkar.Lynkar.dto.user.UserAuthDataDTO;
import com.br.lynkar.Lynkar.dto.user.UserCreateDTO;
import com.br.lynkar.Lynkar.dto.user.UserResponseDTO;
import com.br.lynkar.Lynkar.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserResponseDTO entityToDTO(UserEntity userEntity);
	
	@Mapping(source = "password", target = "passwordHash")
	UserAuthDataDTO entityToAuthDTO(UserEntity userEntity);
	
	UserEntity dtoToEntity(UserCreateDTO userDto);
	

}
