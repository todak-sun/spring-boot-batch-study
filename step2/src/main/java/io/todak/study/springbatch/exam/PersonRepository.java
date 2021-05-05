package io.todak.study.springbatch.exam;

import io.todak.study.springbatch.part3.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {

}
