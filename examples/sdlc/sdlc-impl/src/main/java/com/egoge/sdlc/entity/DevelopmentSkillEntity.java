package com.egoge.sdlc.entity;;

import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = DevelopmentSkillEntity.TABLE_NAME)
public class DevelopmentSkillEntity implements DevelopmentSkill, Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "development_skill";
  public static final String COLUMN_ID = "id";
  public static final String COLUMN_NAME = "skill_name";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = COLUMN_ID, nullable = false, updatable=false)
  @Expose
  private Integer id;

  @Column(name = COLUMN_NAME, nullable = false)
  @Expose
  private String skillName;

  // Default Constructor
  public DevelopmentSkillEntity() {
  }

  // Constructor
  public DevelopmentSkillEntity(String skillName) {
    this.skillName = skillName;
  }

  // Getters and Setters
  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String getSkillName() {
    return skillName;
  }

  @Override
  public void setSkillName(String skillName) {
    this.skillName = skillName;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DevelopmentSkillEntity that = (DevelopmentSkillEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(skillName, that.skillName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, skillName);
  }

  @Override
  public String toString() {
    return "DevelopmentSkillEntity{" +
        "id=" + id +
        ", skillName='" + skillName + '\'' +
        '}';
  }
}
