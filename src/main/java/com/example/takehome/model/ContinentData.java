package com.example.takehome.model;

import java.util.List;

import lombok.Getter;

@Getter
public class ContinentData {

	private ContinentCodeData data;

	@Getter
	public class ContinentCodeData {
		
		private List<Country> countries;
		
		@Getter
		public static class Country {
			
			private Continent continent;
			
			@Getter
			public class Continent {
				private String code;
			}
		}
	}
}
