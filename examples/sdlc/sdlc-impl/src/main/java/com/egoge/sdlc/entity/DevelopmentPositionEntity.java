package com.egoge.sdlc.entity;

import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = DevelopmentPositionEntity.TABLE_NAME)
public class DevelopmentPositionEntity implements DevelopmentPosition<DevelopmentSkillEntity>, Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "development_position";
  public static final String COLUMN_ID = "id";
  public static final String COLUMN_TITLE = "title";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = COLUMN_ID, nullable = false, updatable=false)
  @Expose
  private Integer id;

  @Column(name = COLUMN_TITLE, nullable = false)
  @Expose
  private String title;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "position_skills",
      joinColumns = @JoinColumn(name = "position_id", referencedColumnName = COLUMN_ID, nullable = false, insertable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = DevelopmentSkillEntity.COLUMN_ID, nullable = false, insertable = false, updatable = false)
  )
  @Expose
  private List<DevelopmentSkillEntity> requiredSkills;

  public DevelopmentPositionEntity() {
  }

  public DevelopmentPositionEntity(String title) {
    this.title = title;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public List<DevelopmentSkillEntity> getRequiredSkills() {
    return requiredSkills;
  }

  @Override
  public void setRequiredSkills(List<DevelopmentSkillEntity> requiredSkills) {
    this.requiredSkills = requiredSkills;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DevelopmentPositionEntity that = (DevelopmentPositionEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(requiredSkills, that.requiredSkills);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, requiredSkills);
  }

  @Override
  public String toString() {
    return "DevelopmentPositionEntity{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", requiredSkills=" + requiredSkills +
        '}';
  }
}
