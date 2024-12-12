package com.egoge.sdlc.mapper;

import com.egoge.sdlc.dto.DeveloperDto;
import com.egoge.sdlc.dto.PositionDto;
import com.egoge.sdlc.dto.SkillDto;
import com.egoge.sdlc.entity.DeveloperEntity;
import com.egoge.sdlc.entity.DevelopmentPositionEntity;
import com.egoge.sdlc.entity.DevelopmentSkillEntity;
import java.util.List;
import java.util.stream.Collectors;

public class DeveloperMapperImpl implements DeveloperMapper<DeveloperEntity> {

  @Override
  public DeveloperDto toDto(DeveloperEntity developer) {
    if (developer == null) {
      return null;
    }

    // Map position
    PositionDto positionDto = null;
    DevelopmentPositionEntity position = developer.getPosition();
    if (position != null) {
      positionDto = new PositionDto(
          position.getId(),
          position.getTitle(),
          mapSkills(position.getRequiredSkills())
      );
    }

    // Map developer
    return new DeveloperDto(
        developer.getId(),
        developer.getFirstName(),
        developer.getLastName(),
        developer.getStartDate(),
        developer.getEndDate(),
        positionDto,
        mapSkills(developer.getSkills())
    );
  }

  /**
   * Helper method to map a list of DevelopmentSkillEntity to a list of SkillDto.
   */
  private List<SkillDto> mapSkills(List<DevelopmentSkillEntity> skills) {
    if (skills == null || skills.isEmpty()) {
      return List.of();
    }

    return skills.stream()
        .map(skill -> new SkillDto(skill.getId(), skill.getSkillName()))
        .collect(Collectors.toList());
  }
}
