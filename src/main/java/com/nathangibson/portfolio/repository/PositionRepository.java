package com.nathangibson.portfolio.repository;

import com.nathangibson.portfolio.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository
    extends JpaRepository<PositionEntity, Long> {

  public List<PositionEntity> findByUserId(long userId);
}
