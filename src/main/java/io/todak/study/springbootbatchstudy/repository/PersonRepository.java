package io.todak.study.springbootbatchstudy.repository;

import io.todak.study.springbootbatchstudy.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByFirstName(String firstName);
}
