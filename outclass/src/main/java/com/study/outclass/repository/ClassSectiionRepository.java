package com.study.outclass.repository;

import com.study.outclass.Entity.ClassInfo;
import com.study.outclass.Entity.ClassSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassSectiionRepository extends JpaRepository<ClassSection, Long> {

    Optional<ClassInfo> findByClassSectionNo(Long classSectionNo);

    List<ClassSection> findByClassNo(Long classNo);
}
