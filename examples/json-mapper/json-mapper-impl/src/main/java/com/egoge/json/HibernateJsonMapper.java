package com.egoge.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Qualifier("HibernateJsonMapper")
public class HibernateJsonMapper implements JsonMapper {
  private final Gson gson;

  public HibernateJsonMapper() {
    this.gson = new GsonBuilder()
        .setPrettyPrinting()
        // Register the Hibernate Proxy adapter factory
        .registerTypeAdapterFactory(new HibernateProxyAdapterFactory())
        // Register the LocalDate adapter
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();
  }

  @Override
  public String domainToJson(Object entity) {
    return gson.toJson(entity);
  }
}
