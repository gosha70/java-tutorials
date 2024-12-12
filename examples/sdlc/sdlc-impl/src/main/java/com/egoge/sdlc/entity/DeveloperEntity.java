package com.egoge.sdlc.entity;

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
import com.google.gson.annotations.Expose;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = DeveloperEntity.TABLE_NAME)
public class DeveloperEntity implements Developer<DevelopmentPositionEntity, DevelopmentSkillEntity>, Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "developer";
  public static final String COLUMN_ID = "id";
  public static final String COLUMN_FIRST_NAME = "first_name";
  public static final String COLUMN_LAST_NAME = "last_name";
  public static final String COLUMN_START_DATE = "start_date";
  public static final String COLUMN_END_DATE = "end_date";
  public static final String COLUMN_POSITION_ID = "position_id";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = COLUMN_ID, nullable = false, updatable = false)
  @Expose
  private Integer id;

  @Column(name = COLUMN_FIRST_NAME, nullable = false)
  @Expose
  private String firstName;

  @Column(name = COLUMN_LAST_NAME, nullable = false)
  @Expose
  private String lastName;

  @Column(name = COLUMN_START_DATE)
  @Expose
  private LocalDate startDate;

  @Column(name = COLUMN_END_DATE)
  @Expose
  private LocalDate endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = COLUMN_POSITION_ID, referencedColumnName = DevelopmentPositionEntity.COLUMN_ID, nullable = false, insertable = false, updatable = false)
  @Expose
  private DevelopmentPositionEntity position;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "developer_skills",
      joinColumns = @JoinColumn(name = "developer_id", referencedColumnName = COLUMN_ID, nullable = false, insertable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = DevelopmentSkillEntity.COLUMN_ID, nullable = false, insertable = false, updatable = false)
  )
  @Expose
  private List<DevelopmentSkillEntity> skills;

  public DeveloperEntity() {
  }

  public DeveloperEntity(
      String firstName,
      String lastName,
      LocalDate startDate,
      LocalDate endDate,
      DevelopmentPositionEntity position) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.position = position;
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
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public LocalDate getStartDate() {
    return startDate;
  }

  @Override
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  @Override
  public LocalDate getEndDate() {
    return endDate;
  }

  @Override
  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Override
  public DevelopmentPositionEntity getPosition() {
    return position;
  }

  @Override
  public void setPosition(DevelopmentPositionEntity position) {
    this.position = position;

  }

  @Override
  public List<DevelopmentSkillEntity> getSkills() {
    return skills;
  }

  @Override
  public void setSkills(List<DevelopmentSkillEntity> skills) {
    this.skills = skills;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeveloperEntity that = (DeveloperEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(position, that.position) && Objects.equals(skills, that.skills);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, startDate, endDate, position, skills);
  }

  @Override
  public String toString() {
    return "DeveloperEntity{" +
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
