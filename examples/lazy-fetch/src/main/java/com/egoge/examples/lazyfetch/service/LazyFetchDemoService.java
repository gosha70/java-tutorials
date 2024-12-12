package com.egoge.examples.lazyfetch.service;

import com.egoge.json.JsonMapper;
import com.egoge.sdlc.dto.DeveloperDto;
import com.egoge.sdlc.entity.Developer;
import com.egoge.sdlc.entity.DeveloperEntity;
import com.egoge.sdlc.entity.DevelopmentPositionEntity;
import com.egoge.sdlc.entity.DevelopmentSkillEntity;
import com.egoge.sdlc.mapper.DeveloperMapper;
import com.egoge.sdlc.mapper.DeveloperMapperImpl;
import com.egoge.sdlc.service.DeveloperService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service
public class LazyFetchDemoService {
  private static final Logger LOG = LogManager.getLogger(LazyFetchDemoService.class);

  private final DeveloperService<DeveloperEntity> developerService;
  private final DeveloperMapper<DeveloperEntity> developerMapper = new DeveloperMapperImpl();

  public LazyFetchDemoService(DeveloperService<DeveloperEntity> developerService) {
    this.developerService = developerService;
  }

  /**
   * Demonstrates the issue with lazy fetched references during converting an entity to JSON.
   */
  public Developer<DevelopmentPositionEntity, DevelopmentSkillEntity> findDeveloper_error() {
    return queryDeveloperGetAllFields(1);
  }

  /**
   * Demonstrates the solution to work around the issue with lazy fetched references by using
   * <tt>@Transactional</tt> and converting {@link Developer} to {@link DeveloperDto}.
   */
  @Transactional
  public DeveloperDto findDeveloper_transaction_dto() {
    DeveloperEntity developer = developerService.getDeveloperById(2);
    return developerMapper.toDto(developer);
  }

  /**
   * Demonstrates the solution to work around the issue with lazy fetched references by using
   * <tt>@Transactional</tt> with getting all lazy fetched referenced objects.
   */
  @Transactional
  public Developer<DevelopmentPositionEntity, DevelopmentSkillEntity> findDeveloper_transaction_getter() {
    return queryDeveloperGetAllFields(2);
  }

  /**
   * Demonstrates the solution to work around the issue with lazy fetched references by using
   * <tt>@Transactional</tt> with calling {@link Hibernate#initialize(Object)} on all lazy fetched referenced objects.
   */
  @Transactional
  public Developer<DevelopmentPositionEntity, DevelopmentSkillEntity> findDeveloper_transaction_hibernate() {
    return queryDeveloperHibernateAllFields(1);
  }

  /**
   * Demonstrates the solution of converting the fetched {@link Developer} to JSON by using
   * <tt>@Transactional</tt>.
   */
  @Transactional
  public String developerToJson_transaction(JsonMapper jsonMapper) {
    DeveloperEntity developer = developerService.getDeveloperById(1);
    LOG.info("Queried Developer: {} {}", developer.getFirstName(), developer.getLastName());

    return jsonMapper.domainToJson(developer);
  }

  private Developer<DevelopmentPositionEntity, DevelopmentSkillEntity> queryDeveloperGetAllFields(int developerId) {
    DeveloperEntity developer = developerService.getDeveloperById(developerId);
    LOG.info("Queried Developer: {} {}", developer.getFirstName(), developer.getLastName());

    // Access a lazy-loaded position
    final StringBuilder buf1 = new StringBuilder();
    developer.getPosition().getRequiredSkills().forEach(skill ->
        buf1.append("\t\t CLASS[").append(skill.getClass().getName()).append("] - ").append(skill.getSkillName())
    );
    LOG.info("Accessing Position: CLASS[{}] - {} ({})", developer.getPosition().getClass().getName(), developer.getPosition().getTitle(), buf1.toString());

    // Access a lazy-loaded developer's skills
    final StringBuilder buf2 = new StringBuilder();
    developer.getSkills().forEach(skill ->
        buf2.append("\t\t CLASS[").append(skill.getClass().getName()).append("] - ").append(skill.getSkillName())
    );
    LOG.info("Accessing Skills: {}", buf2.toString());

    return developer;
  }

  private Developer<DevelopmentPositionEntity, DevelopmentSkillEntity> queryDeveloperHibernateAllFields(int developerId) {
    DeveloperEntity developer = developerService.getDeveloperById(developerId);
    LOG.info("Queried Developer: {} {}", developer.getFirstName(), developer.getLastName());

    Hibernate.initialize(developer.getPosition());
    Hibernate.initialize(developer.getPosition().getRequiredSkills());
    Hibernate.initialize(developer.getSkills());

    return developer;
  }
}