package com.egoge.sdlc.service;

import com.egoge.sdlc.entity.Developer;

import java.util.List;

public interface DeveloperService<T extends Developer> {

  T createDeveloper(T developer);

  T updateDeveloper(Integer id, T developer);

  T getDeveloperById(Integer id);

  List<T> getAllDevelopers();

  void deleteDeveloper(Integer id);
}