package com.egoge.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;

public class HibernateProxyAdapterFactory implements TypeAdapterFactory {
  @Override
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
    Class<? super T> objectType = typeToken.getRawType();
    if (HibernateProxy.class.isAssignableFrom(objectType)) {
      return new HibernateProxyAdapter<>(gson);
    }
    // For non-HibernateProxy types, return null to let Gson handle them
    return null;
  }
}

