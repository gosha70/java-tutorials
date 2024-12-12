package com.egoge.sdlc.service;

import com.egoge.sdlc.dao.DeveloperDao;
import com.egoge.sdlc.entity.DeveloperEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeveloperServiceImpl implements DeveloperService<DeveloperEntity> {
  private final DeveloperDao<DeveloperEntity> developerDao;

  public DeveloperServiceImpl(DeveloperDao<DeveloperEntity> developerDao) {
    this.developerDao = developerDao;
  }

  @Override
  @Transactional
  public DeveloperEntity createDeveloper(DeveloperEntity developer) {
    return developerDao.save(developer);
  }

  @Override
  @Transactional
  public DeveloperEntity updateDeveloper(Integer id, DeveloperEntity developer) {
    DeveloperEntity existing = developerDao.find(id).orElseThrow(() -> new IllegalArgumentException("Developer not found"));
    existing.setFirstName(developer.getFirstName());
    existing.setLastName(developer.getLastName());
    existing.setEndDate(developer.getEndDate());
    existing.setStartDate(developer.getStartDate());
    existing.setPosition(developer.getPosition());
    existing.setSkills(developer.getSkills());

    return developerDao.save(existing);
  }

  @Override
  @Transactional
  public DeveloperEntity getDeveloperById(Integer id) {
    return developerDao.find(id).orElseThrow(() -> new IllegalArgumentException("Developer not found"));
  }

  @Override
  @Transactional
  public List<DeveloperEntity> getAllDevelopers() {
    return developerDao.findAll();
  }

  @Override
  @Transactional
  public void deleteDeveloper(Integer id) {
    developerDao.delete(id);
  }
}