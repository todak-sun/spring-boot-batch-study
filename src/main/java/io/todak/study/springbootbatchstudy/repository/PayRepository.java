package io.todak.study.springbootbatchstudy.repository;

import io.todak.study.springbootbatchstudy.entities.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Long> {
}
