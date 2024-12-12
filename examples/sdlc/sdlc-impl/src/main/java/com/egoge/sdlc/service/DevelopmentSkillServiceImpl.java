package com.egoge.sdlc.service;

import com.egoge.sdlc.dao.DevelopmentSkillDao;
import com.egoge.sdlc.entity.DevelopmentSkillEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevelopmentSkillServiceImpl implements DevelopmentSkillService<DevelopmentSkillEntity> {
  private final DevelopmentSkillDao<DevelopmentSkillEntity> skillDao;

  public DevelopmentSkillServiceImpl(DevelopmentSkillDao<DevelopmentSkillEntity> skillDao) {
    this.skillDao = skillDao;
  }

  @Override
  @Transactional
  public DevelopmentSkillEntity createSkill(DevelopmentSkillEntity skill) {
    return skillDao.save(skill);
  }

  @Override
  @Transactional
  public DevelopmentSkillEntity updateSkill(Integer id, DevelopmentSkillEntity skill) {
    DevelopmentSkillEntity existing = skillDao.find(id).orElseThrow(() -> new IllegalArgumentException("Skill not found"));
    existing.setSkillName(skill.getSkillName());
    return skillDao.save(existing);
  }

  @Override
  @Transactional
  public DevelopmentSkillEntity getSkillById(Integer id) {
    return skillDao.find(id).orElseThrow(() -> new IllegalArgumentException("Skill not found"));
  }

  @Override
  @Transactional
  public List<DevelopmentSkillEntity> getAllSkills() {
    return skillDao.findAll();
  }

  @Override
  @Transactional
  public void deleteSkill(Integer id) {
    skillDao.delete(id);
  }
}

