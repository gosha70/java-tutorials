package com.egoge.sdlc.entity;

import java.util.List;

public interface DevelopmentPosition<T extends DevelopmentSkill> {
  Integer getId();

  void setId(Integer id);

  String getTitle();

  void setTitle(String title);

  List<T> getRequiredSkills();

  void setRequiredSkills(List<T> requiredSkills);
}
