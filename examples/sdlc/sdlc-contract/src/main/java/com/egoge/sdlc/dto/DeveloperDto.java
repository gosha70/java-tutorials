package com.egoge.sdlc.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DeveloperDto {
  private Integer id;
  private String firstName;
  private String lastName;
  private LocalDate startDate;
  private LocalDate endDate;
  private PositionDto position;
  private List<SkillDto> skills;

  // Constructors
  public DeveloperDto() {}

  public DeveloperDto(
      Integer id,
      String firstName,
      String lastName,
      LocalDate startDate,
      LocalDate endDate,
      PositionDto position,
      List<SkillDto> skills
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.position = position;
    this.skills = skills;
  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public PositionDto getPosition() {
    return position;
  }

  public void setPosition(PositionDto position) {
    this.position = position;
  }

  public List<SkillDto> getSkills() {
    return skills;
  }

  public void setSkills(List<SkillDto> skills) {
    this.skills = skills;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeveloperDto that = (DeveloperDto) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(firstName, that.firstName) &&
        Objects.equals(lastName, that.lastName) &&
        Objects.equals(startDate, that.startDate) &&
        Objects.equals(endDate, that.endDate) &&
        Objects.equals(position, that.position) &&
        Objects.equals(skills, that.skills);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, startDate, endDate, position, skills);
  }

  @Override
  public String toString() {
    return "DeveloperDto{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", position=" + position +
        ", skills=" + skills +
        '}';
  }
}
