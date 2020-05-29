package com.evliion.ev.service;

import com.evliion.ev.payload.marqeta.CreateCardRequest;
import com.evliion.ev.payload.marqeta.CreateUserRequest;
import com.evliion.ev.payload.marqeta.MarqetaResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarqetaService {
  private String serverUrl;

  private String appToken;

  private String accessToken;

  private RestTemplate restTemplate;

  private HttpHeaders headers;

  private String cardProductToken;

  private String fundingSourceToken;

  private static final Logger logger = LoggerFactory.getLogger(MarqetaService.class);

  public MarqetaService(@Value("${marqeta.url}") String serverUrl,
                        @Value("${marqeta.application.token}") String appToken,
                        @Value("${marqeta.master.access.token}") String accessToken,
                        @Value("${marqeta.card.product.token}") String cardProductToken,
                        @Value("${marqeta.funding.source.token}") String fundingSourceToken,
                        RestTemplate restTemplate) {
    this.serverUrl = serverUrl;
    this.appToken = appToken;
    this.accessToken = accessToken;
    this.restTemplate = restTemplate;
    this.cardProductToken = cardProductToken;
    this.fundingSourceToken = fundingSourceToken;
    headers = getHeaders(this.appToken, this.accessToken);
  }

  public String createUser(CreateUserRequest userRequest) {
    ResponseEntity<MarqetaResponse> response = restTemplate.exchange(serverUrl + "/users", HttpMethod.POST,
        new HttpEntity<>(userRequest, headers), MarqetaResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in creating user. Error message : " + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in creating user. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Created user successfully");
      return Objects.requireNonNull(response.getBody()).getToken();
    }
  }

  public String createCard(String userToken) {
    CreateCardRequest createCardRequest = new CreateCardRequest(userToken, cardProductToken);
    ResponseEntity<MarqetaResponse> response = restTemplate.exchange(serverUrl +"/cards", HttpMethod.POST,
        new HttpEntity<>(createCardRequest, headers), MarqetaResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in creating card. Error message : " + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in creating card. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Created card successfully");
      return Objects.requireNonNull(response.getBody()).getToken();
    }
  }

  private HttpHeaders getHeaders(String username, String password) {
    String credentials = username + ":" + password;
    byte[] credentialsBytes = credentials.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(credentialsBytes);
    String base64Creds = new String(base64CredsBytes);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Basic " + base64Creds);
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return headers;
  }
}
