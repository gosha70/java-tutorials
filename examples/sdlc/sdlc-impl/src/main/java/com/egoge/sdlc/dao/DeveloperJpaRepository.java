package com.egoge.sdlc.dao;

import com.egoge.sdlc.entity.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperJpaRepository extends JpaRepository<DeveloperEntity, Integer> {
}