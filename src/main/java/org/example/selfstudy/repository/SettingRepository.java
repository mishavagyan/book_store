package org.example.selfstudy.repository;

import org.example.selfstudy.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
    List<Setting> findByNameContainingIgnoreCase(String keyword);
}
