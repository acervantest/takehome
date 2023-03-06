package com.example.takehome.model;

import java.util.List;
import com.example.takehome.model.CountryData.CountryCodeData.Continent.Country;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContinentDetails {

	private String name;
	private List<String> countries;
	private List<Country> otherCountries;
}
