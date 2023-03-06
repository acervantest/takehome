package com.example.takehome.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.takehome.model.ContinentData;
import com.example.takehome.model.CountryData;
import com.example.takehome.model.CountryData.CountryCodeData.Continent;
import com.example.takehome.model.ContinentData.ContinentCodeData.Country;
import com.example.takehome.graphqlservice.GraphqlService;
import com.example.takehome.model.ApiResponseDto;
import com.example.takehome.model.ContinentDetails;

@Service
public class ContinentService {
	
	@Autowired
	GraphqlService graphqlService;
	
	public ApiResponseDto getContinentInformation( final String[] countryCodes ) throws IOException, InterruptedException, ExecutionException, TimeoutException {
		
		ContinentData continentData = graphqlService.getContinentCodes( countryCodes );//fetch continent information by country code
		
		Set<String> continentCodesSet = new HashSet<>();
		
		for(Country country: continentData.getData().getCountries())//extract continent code and add it to a set
			continentCodesSet.add( country.getContinent().getCode() );
		
		String[] continentCodesArray = new String[ continentCodesSet.size() ];
		
		continentCodesSet.toArray( continentCodesArray );
		
		CountryData countryData = graphqlService.getCountryCodes( continentCodesArray );//fetch continent name and countries list by continent code
		
		List<ContinentDetails> continentDetailsList = setContinentDetails( countryData, countryCodes );//build response entity
		
		ApiResponseDto apiResponseDto = new ApiResponseDto(); 
		
		apiResponseDto.setContinent( continentDetailsList );
		
		return apiResponseDto;
	}
	
	public List<ContinentDetails> setContinentDetails( CountryData countryCodeDto, String[] countryCodes ) {
		
		List<Continent> continents = countryCodeDto.getData().getContinents();
		
		List<ContinentDetails> continents_info = new ArrayList<>();
		
		for( Continent continent:continents ) {
			ContinentDetails continentDetail = buildContinentDetail( continent, countryCodes );
			continents_info.add( continentDetail );
		}
		
		return continents_info;
	}
	
	public ContinentDetails buildContinentDetail( Continent continent, String[] countryCodes ) {
		
		ContinentDetails continentInfoDto = new ContinentDetails();
		
		continentInfoDto.setName( continent.getName() );
		
		List<String> continent_countries = new ArrayList<>();
		
		for( String countryCode:countryCodes ) {
			
			for(int i = 0; i < continent.getCountries().size(); i++) {
				if(countryCode.equals(continent.getCountries().get(i).getCode())) {
					continent_countries.add( countryCode );	
					continent.getCountries().remove( i );
				}
			}
		
		}
		
		continentInfoDto.setCountries( continent_countries );
		continentInfoDto.setOtherCountries( continent.getCountries() );
		
		return continentInfoDto;
	}
	
}
