package com.example.takehome.graphqlservice;

import static com.example.takehome.utils.Constants.GRAPHQL_END_POINT; 
import static com.example.takehome.utils.Constants.CONTINENT_CODE_QUERY;
import static com.example.takehome.utils.Constants.CONTINENT_CODE_VARS_KEY;
import static com.example.takehome.utils.Constants.COUNTRY_CODE_QUERY;
import static com.example.takehome.utils.Constants.COUNTRY_CODE_VARS_KEY;


import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.takehome.model.ContinentData;
import com.example.takehome.model.CountryData;
import com.example.takehome.utils.GraphqlRequestBody;
import com.example.takehome.utils.GraphqlSchemaReaderUtil;
import com.google.gson.Gson;

import reactor.core.scheduler.Schedulers;

@Service
public class GraphqlService {
	
	private final String url;
	private WebClient webClient;
	
	public GraphqlService( @Value( GRAPHQL_END_POINT ) String url) {
	    this.url = url;
	    this.webClient = WebClient.builder().build();
	}
	
	public ContinentData getContinentCodes( String[] countryCodes ) throws IOException, InterruptedException, ExecutionException, TimeoutException {

	    GraphqlRequestBody graphQLRequestBody = buildGraphqlBodyRequest( CONTINENT_CODE_QUERY, CONTINENT_CODE_VARS_KEY, countryCodes );
	    
	    return webClient.post()
	        .uri(url)
	        .bodyValue(graphQLRequestBody)
	        .retrieve()
	        .bodyToMono(ContinentData.class)
	        .subscribeOn(Schedulers.boundedElastic()).toFuture().get(5L, TimeUnit.SECONDS);     
    }

	public CountryData getCountryCodes( String[] cCodes ) throws IOException, InterruptedException, ExecutionException, TimeoutException {

	    GraphqlRequestBody graphQLRequestBody = buildGraphqlBodyRequest( COUNTRY_CODE_QUERY, COUNTRY_CODE_VARS_KEY, cCodes);
	   
	    return webClient.post()
	        .uri(url)
	        .bodyValue(graphQLRequestBody)
	        .retrieve()
	        .bodyToMono(CountryData.class)
	        .subscribeOn(Schedulers.boundedElastic()).toFuture().get(5L, TimeUnit.SECONDS);     
    }
	
	public GraphqlRequestBody buildGraphqlBodyRequest( String schemaName, String variablesKey, String[] variablesList ) throws IOException {
		
		GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

		final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName(schemaName);
		final String vars = "{ \""+ variablesKey +"\": "+new Gson().toJson( variablesList )+" }";
		
		graphQLRequestBody.setQuery(query);
		graphQLRequestBody.setVariables(vars);
		
		return graphQLRequestBody;
	}

}
