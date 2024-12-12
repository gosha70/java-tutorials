package com.egoge.sdlc.dao;

import com.egoge.sdlc.entity.DeveloperEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DeveloperDaoImpl implements DeveloperDao<DeveloperEntity> {

  private final DeveloperJpaRepository repository;

  public DeveloperDaoImpl(DeveloperJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public DeveloperEntity save(DeveloperEntity developer) {
    return repository.save(developer);
  }

  @Override
  public Optional<DeveloperEntity> find(Integer id) {
    return repository.findById(id);
  }

  @Override
  public List<DeveloperEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public void delete(Integer id) {
    repository.deleteById(id);
  }
}