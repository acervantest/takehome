package com.example.takehome.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.takehome.model.ApiResponseDto;
import com.example.takehome.service.ContinentService;

@RestController
@RequestMapping("/continent-api")
public class ContinentController {
	
	@Autowired
	ContinentService continentService;

	@GetMapping("/continents")
	public ResponseEntity<ApiResponseDto> getContinents( @RequestParam String[] countryCodes ) throws IOException, InterruptedException, ExecutionException, TimeoutException {
		
		ApiResponseDto apiResponseDto = continentService.getContinentInformation( countryCodes );
		
		return ResponseEntity.ok().body( apiResponseDto );
	}
	
}
