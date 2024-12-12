package com.egoge.sdlc.dto;

import java.util.List;
import java.util.Objects;

public class PositionDto {
  private Integer id;
  private String title;
  private List<SkillDto> requiredSkills;

  // Constructors
  public PositionDto() {}

  public PositionDto(Integer id, String title, List<SkillDto> requiredSkills) {
    this.id = id;
    this.title = title;
    this.requiredSkills = requiredSkills;
  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<SkillDto> getRequiredSkills() {
    return requiredSkills;
  }

  public void setRequiredSkills(List<SkillDto> requiredSkills) {
    this.requiredSkills = requiredSkills;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PositionDto that = (PositionDto) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(title, that.title) &&
        Objects.equals(requiredSkills, that.requiredSkills);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, requiredSkills);
  }

  @Override
  public String toString() {
    return "PositionDto{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", requiredSkills=" + requiredSkills +
        '}';
  }
}
