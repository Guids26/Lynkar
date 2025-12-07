package com.br.lynkar.Lynkar.mapper;

import org.mapstruct.Mapper;

import com.br.lynkar.Lynkar.dto.auth.AuthResponseDTO;
import com.br.lynkar.Lynkar.dto.user.UserAuthDataDTO;

@Mapper(componentModel = "spring")
public interface AuthMapper {
	
	AuthResponseDTO userDataToAuthResponse(UserAuthDataDTO user);

}
