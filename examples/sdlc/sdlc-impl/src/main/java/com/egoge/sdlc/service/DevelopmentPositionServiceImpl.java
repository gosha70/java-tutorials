package com.egoge.sdlc.service;

import com.egoge.sdlc.dao.DevelopmentPositionDao;
import com.egoge.sdlc.entity.DevelopmentPositionEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevelopmentPositionServiceImpl implements DevelopmentPositionService<DevelopmentPositionEntity> {
  private final DevelopmentPositionDao<DevelopmentPositionEntity> positionDao;

  public DevelopmentPositionServiceImpl(DevelopmentPositionDao<DevelopmentPositionEntity> positionDao) {
    this.positionDao = positionDao;
  }

  @Override
  @Transactional
  public DevelopmentPositionEntity createPosition(DevelopmentPositionEntity position) {
    return positionDao.save(position);
  }

  @Override
  @Transactional
  public DevelopmentPositionEntity updatePosition(Integer id, DevelopmentPositionEntity position) {
    DevelopmentPositionEntity existing = positionDao.find(id).orElseThrow(() -> new IllegalArgumentException("Position not found"));
    existing.setTitle(position.getTitle());
    existing.setRequiredSkills(position.getRequiredSkills());

    return positionDao.save(existing);
  }

  @Override
  @Transactional
  public DevelopmentPositionEntity getPositionById(Integer id) {
    return positionDao.find(id).orElseThrow(() -> new IllegalArgumentException("Position not found"));
  }

  @Override
  @Transactional
  public List<DevelopmentPositionEntity> getAllPositions() {
    return positionDao.findAll();
  }

  @Override
  @Transactional
  public void deletePosition(Integer id) {
    positionDao.delete(id);
  }
}
