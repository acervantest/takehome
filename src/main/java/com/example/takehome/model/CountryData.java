package com.example.takehome.model;

import java.util.List;

import lombok.Getter;

@Getter
public class CountryData {

	private CountryCodeData data;
	
	@Getter
	public static class CountryCodeData {
	
		private List<Continent> continents;
		
		@Getter
		public static class Continent {
			private String name;
			private List<Country> countries;
			
			@Getter
			public static class Country {
				private String code;
			}
		}
	}
}
