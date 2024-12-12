package com.egoge.sdlc.service;


import com.egoge.sdlc.entity.DevelopmentSkill;

import java.util.List;

public interface DevelopmentSkillService<T extends DevelopmentSkill> {

  T createSkill(T skill);

  T updateSkill(Integer id, T skill);

  T getSkillById(Integer id);

  List<T> getAllSkills();

  void deleteSkill(Integer id);
}
