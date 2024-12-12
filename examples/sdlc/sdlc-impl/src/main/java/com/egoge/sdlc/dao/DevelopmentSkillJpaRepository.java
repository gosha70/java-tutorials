package com.egoge.sdlc.dao;

import com.egoge.sdlc.entity.DevelopmentSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevelopmentSkillJpaRepository extends JpaRepository<DevelopmentSkillEntity, Integer> {
}