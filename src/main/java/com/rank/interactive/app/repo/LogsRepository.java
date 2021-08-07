package com.rank.interactive.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rank.interactive.app.model.Logs;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {

}
