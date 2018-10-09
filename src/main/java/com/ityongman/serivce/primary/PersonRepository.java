package com.ityongman.serivce.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ityongman.domain.primary.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
}
