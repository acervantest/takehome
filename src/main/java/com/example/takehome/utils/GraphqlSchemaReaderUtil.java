package com.example.takehome.utils;

import java.io.IOException;


public final class GraphqlSchemaReaderUtil {

  public static String getSchemaFromFileName(final String filename) throws IOException {
    
	  String res = new String(
        GraphqlSchemaReaderUtil.class.getClassLoader()
        .getResourceAsStream(filename + ".graphql").readAllBytes());
    
	  return res;
  }
}
