package com.egoge.sdlc.dao;
import com.egoge.sdlc.entity.Developer;

import java.util.List;
import java.util.Optional;

public interface DeveloperDao<T extends Developer> {
  T save(T developer);
  Optional<T> find(Integer id);
  List<T> findAll();
  void delete(Integer id);
}