package com.egoge.sdlc.dao;

import com.egoge.sdlc.entity.DevelopmentPosition;

import java.util.List;
import java.util.Optional;

public interface DevelopmentPositionDao<T extends DevelopmentPosition> {

  T save(T position);

  Optional<T> find(Integer id);

  List<T> findAll();

  void delete(Integer id);
}