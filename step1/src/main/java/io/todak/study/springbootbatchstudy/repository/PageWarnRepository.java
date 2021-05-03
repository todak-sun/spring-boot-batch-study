package io.todak.study.springbootbatchstudy.repository;


import io.todak.study.springbootbatchstudy.entities.PageWarn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageWarnRepository extends JpaRepository<PageWarn, Long> {

//    @Query("SELECT p FROM PageWarn p WHERE p.successStatus = true")
//    List<PageWarn> findAllSuccess();

}
