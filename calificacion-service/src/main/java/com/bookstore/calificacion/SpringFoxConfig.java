package com.bookstore.calificacion;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {
	@Autowired
	PasswordEncoder passwordEncoder;
	final String AUTH_SERVER = "http://localhost:8080/oauth";
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.bookstore.calificacion")).paths(PathSelectors.any())
				.build();
//				.securitySchemes(Arrays.asList(securityScheme()))
//				.securityContexts(Arrays.asList(securityContext()));
	}
//	@Bean
//	public SecurityConfiguration security() {
//	    return SecurityConfigurationBuilder.builder()
//	        .clientId("angularapp")
//	        .clientSecret(passwordEncoder.encode("12345"))
//	        .scopeSeparator(" ")
//	        .useBasicAuthenticationWithAccessCodeGrant(true)
//	        .build();
//	}
//	private SecurityScheme securityScheme() {
//	    GrantType grantType = new AuthorizationCodeGrantBuilder()
//	        .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
//	        .tokenRequestEndpoint(
//	          new TokenRequestEndpoint(AUTH_SERVER + "/authorize", "angularapp", passwordEncoder.encode("12345")))
//	        .build();
//	 
//	    SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
//	        .grantTypes(Arrays.asList(grantType))
//	        .scopes(Arrays.asList(scopes()))
//	        .build();
//	    return oauth;
//	}
//	private AuthorizationScope[] scopes() {
//	    AuthorizationScope[] scopes = { 
//	      new AuthorizationScope("read", "for read operations"), 
//	      new AuthorizationScope("write", "for write operations"), 
//	      new AuthorizationScope("foo", "Access foo API") };
//	    return scopes;
//	}
//	private SecurityContext securityContext() {
//	    return SecurityContext.builder()
//	      .securityReferences(
//	        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
//	      .forPaths(PathSelectors.regex("/api.*"))
//	      .build();
//	}
}
