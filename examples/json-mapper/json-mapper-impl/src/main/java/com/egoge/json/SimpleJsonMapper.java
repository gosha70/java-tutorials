package com.egoge.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Qualifier("SimpleJsonMapper")
public class SimpleJsonMapper implements JsonMapper {
  private final Gson gson;

  public SimpleJsonMapper() {
    this.gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();
  }

  @Override
  public String domainToJson(Object entity) {
    return gson.toJson(entity);
  }
}
