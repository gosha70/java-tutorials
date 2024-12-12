package com.egoge.sdlc.dao;

import com.egoge.sdlc.entity.DevelopmentSkill;

import java.util.List;
import java.util.Optional;

public interface DevelopmentSkillDao<T extends DevelopmentSkill> {

  T save(T skill);

  Optional<T> find(Integer id);

  List<T> findAll();

  void delete(Integer id);
}
