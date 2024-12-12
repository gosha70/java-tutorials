package com.egoge.sdlc.dao;

import com.egoge.sdlc.entity.DevelopmentPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevelopmentPositionJpaRepository extends JpaRepository<DevelopmentPositionEntity, Integer> {
}