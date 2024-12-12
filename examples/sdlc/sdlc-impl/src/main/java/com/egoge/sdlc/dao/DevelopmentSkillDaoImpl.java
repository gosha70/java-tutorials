package com.egoge.sdlc.dao;

import com.egoge.sdlc.entity.DevelopmentSkillEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DevelopmentSkillDaoImpl implements DevelopmentSkillDao<DevelopmentSkillEntity> {
  private final DevelopmentSkillJpaRepository repository;

  public DevelopmentSkillDaoImpl(DevelopmentSkillJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public DevelopmentSkillEntity save(DevelopmentSkillEntity skill) {
    return repository.save(skill);
  }

  @Override
  public Optional<DevelopmentSkillEntity> find(Integer id) {
    return repository.findById(id);
  }

  @Override
  public List<DevelopmentSkillEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public void delete(Integer id) {
    repository.deleteById(id);
  }
}
