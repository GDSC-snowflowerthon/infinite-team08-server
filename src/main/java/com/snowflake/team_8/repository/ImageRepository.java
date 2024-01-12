package com.snowflake.team_8.repository;

import com.snowflake.team_8.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAll();
}
