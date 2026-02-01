package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Cricketer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CricketerRepository extends JpaRepository<Cricketer, Integer> {
    List<Cricketer> findByTeamId(int teamId);
}
