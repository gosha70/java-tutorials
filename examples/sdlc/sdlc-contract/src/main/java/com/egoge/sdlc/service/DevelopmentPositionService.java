package com.egoge.sdlc.service;

import com.egoge.sdlc.entity.DevelopmentPosition;

import java.util.List;

public interface DevelopmentPositionService<T extends DevelopmentPosition> {

  T createPosition(T position);

  T updatePosition(Integer id, T position);

  T getPositionById(Integer id);

  List<T> getAllPositions();

  void deletePosition(Integer id);
}
