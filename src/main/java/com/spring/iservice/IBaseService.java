package com.spring.iservice;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.spring.dto.DropDownDataDto;

import com.spring.model.UserDto;

public interface IBaseService {
	public UserDto getUserById(int userId);

	public Map<String, Object> getDropDownData();

	public ResponseEntity<DropDownDataDto> getDropDownSetupData();

}
