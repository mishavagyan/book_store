package org.example.selfstudy.repository;

import org.example.selfstudy.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AwardRepository extends JpaRepository<Award, Integer> {
    List<Award> findByNameIn(Collection<String> names);
}
