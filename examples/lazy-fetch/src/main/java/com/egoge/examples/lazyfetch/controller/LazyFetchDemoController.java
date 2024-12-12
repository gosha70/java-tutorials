package com.egoge.examples.lazyfetch.controller;

import com.egoge.examples.lazyfetch.service.LazyFetchDemoService;
import com.egoge.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/lazy-fetch")
public class LazyFetchDemoController {

  private final LazyFetchDemoService demoService;
  private final JsonMapper simpleJsonMapper;
  private final JsonMapper hibernateJsonMapper;
  private final Gson gson;

  public LazyFetchDemoController(
      LazyFetchDemoService demoService,
      @Qualifier("SimpleJsonMapper") JsonMapper simpleJsonMapper,
      @Qualifier("HibernateJsonMapper") JsonMapper hibernateJsonMapper) {
    this.demoService = demoService;
    this.simpleJsonMapper = simpleJsonMapper;
    this.hibernateJsonMapper = hibernateJsonMapper;
    this.gson = new GsonBuilder().setPrettyPrinting().create();
  }

  @GetMapping("/dev")
  public ResponseEntity<?> lazyLoading_error() {
    return handleChainedRequest(
        false,
        demoService::findDeveloper_error
    );
  }

  @GetMapping("/dev-tx-getter")
  public ResponseEntity<?> lazyLoading_transaction_getter() {
    return handleChainedRequest(
        false,
        demoService::findDeveloper_transaction_getter
    );
  }

  @GetMapping("/dev-tx-hibernate")
  public ResponseEntity<?> lazyLoading_transaction_hibernate() {
    return handleChainedRequest(
        false,
        demoService::findDeveloper_transaction_hibernate
    );
  }

  @GetMapping("/dev-tx-dto")
  public ResponseEntity<?> lazyLoading_transaction_dto() {
    return handleChainedRequest(
        false,
        demoService::findDeveloper_transaction_dto
    );
  }

  @GetMapping("/json-error")
  public ResponseEntity<?> convertEntityToJson_error() {
    return handleChainedRequest(
        true,
        demoService::findDeveloper_transaction_getter,
        simpleJsonMapper::domainToJson
    );
  }

  @GetMapping("/json-tx")
  public ResponseEntity<?> convertEntityToJson_transaction() {
    return handleChainedRequest(
        true,
        () -> demoService.developerToJson_transaction(hibernateJsonMapper)
    );
  }

  @GetMapping("/json-tx-getter")
  public ResponseEntity<?> convertEntityToJson_transaction_getter() {
    return handleChainedRequest(
        true,
        demoService::findDeveloper_transaction_getter,
        hibernateJsonMapper::domainToJson
    );
  }

  @GetMapping("/json-hibernate")
  public ResponseEntity<?> convertEntityToJson_hibernate() {
    return handleChainedRequest(
        true,
        demoService::findDeveloper_transaction_hibernate,
        hibernateJsonMapper::domainToJson
    );
  }

  @GetMapping("/json-dto")
  public ResponseEntity<?> convertEntityToJson_dto() {
    return handleChainedRequest(
        true,
        demoService::findDeveloper_transaction_dto,
        simpleJsonMapper::domainToJson
    );
  }

  private <T> ResponseEntity<?> handleChainedRequest(
      boolean isJsonResponse,
      Supplier<T> initialAction, Function<T, ?>... chainedActions) {
    try {
      T result = initialAction.get();
      Object finalResult = result;

      for (Function<T, ?> action : chainedActions) {
        finalResult = action.apply((T) finalResult);
      }

      return isJsonResponse ? ResponseEntity.ok()
          .header("Content-Type", "application/json")
          .body(finalResult) : ResponseEntity.ok()
          .body(finalResult.toString());
    } catch (Exception e) {
      String errorMessage = "Error occurred: " + e.getMessage();
      String errorJson = gson.toJson(new DemoResponse("error", errorMessage));
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .header("Content-Type", "application/json")
          .body(errorJson);
    }
  }
}
