package com.egoge.sdlc.dao;

import com.egoge.sdlc.entity.DevelopmentPositionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DevelopmentPositionDaoImpl implements DevelopmentPositionDao<DevelopmentPositionEntity> {
  private final DevelopmentPositionJpaRepository repository;

  public DevelopmentPositionDaoImpl(DevelopmentPositionJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public DevelopmentPositionEntity save(DevelopmentPositionEntity position) {
    return repository.save(position);
  }

  @Override
  public Optional<DevelopmentPositionEntity> find(Integer id) {
    return repository.findById(id);
  }

  @Override
  public List<DevelopmentPositionEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public void delete(Integer id) {
    repository.deleteById(id);
  }
}