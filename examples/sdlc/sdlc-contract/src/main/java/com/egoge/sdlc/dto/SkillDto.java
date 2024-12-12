package com.egoge.sdlc.dto;

import java.util.Objects;

public class SkillDto {
  private Integer id;
  private String skillName;

  // Constructors
  public SkillDto() {}

  public SkillDto(Integer id, String skillName) {
    this.id = id;
    this.skillName = skillName;
  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSkillName() {
    return skillName;
  }

  public void setSkillName(String skillName) {
    this.skillName = skillName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SkillDto skillDto = (SkillDto) o;
    return Objects.equals(id, skillDto.id) &&
        Objects.equals(skillName, skillDto.skillName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, skillName);
  }

  @Override
  public String toString() {
    return "SkillDto{" +
        "id=" + id +
        ", skillName='" + skillName + '\'' +
        '}';
  }
}
