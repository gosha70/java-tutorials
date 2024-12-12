package com.egoge.sdlc.entity;

import java.time.LocalDate;
import java.util.List;

public interface Developer<T extends DevelopmentPosition, K extends DevelopmentSkill> {
  Integer getId();

  void setId(Integer id);

  String getFirstName();

  void setFirstName(String firstName);

  String getLastName();

  void setLastName(String lastName);

  LocalDate getStartDate();

  void setStartDate(LocalDate startDate);

  LocalDate getEndDate();

  void setEndDate(LocalDate endDate);

  T getPosition();

  void setPosition(T position);

  List<K> getSkills();

  void setSkills(List<K> skills);
}
