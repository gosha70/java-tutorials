package com.egoge.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;

public class HibernateProxyAdapter<T> extends TypeAdapter<T> {

  private final Gson gson;

  public HibernateProxyAdapter(Gson gson) {
    this.gson = gson;
  }

  @Override
  public void write(JsonWriter out, T value) throws IOException {
    T unproxyValue = (T) Hibernate.unproxy(value);
    gson.toJson(unproxyValue, unproxyValue.getClass(), out);
  }

  @Override
  public T read(JsonReader in) {
    throw new UnsupportedOperationException("Not implemented");
  }
}

