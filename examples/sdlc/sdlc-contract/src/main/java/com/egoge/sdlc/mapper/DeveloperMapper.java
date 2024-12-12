package com.egoge.sdlc.mapper;

import com.egoge.sdlc.dto.DeveloperDto;
import com.egoge.sdlc.entity.Developer;

public interface DeveloperMapper<T extends Developer> {
  DeveloperDto toDto(T developer);
}
