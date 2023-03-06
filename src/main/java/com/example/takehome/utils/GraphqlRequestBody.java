package com.example.takehome.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraphqlRequestBody {
	private String query;
	private Object variables;
}
