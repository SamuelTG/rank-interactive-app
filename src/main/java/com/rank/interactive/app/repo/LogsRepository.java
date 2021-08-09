package com.rank.interactive.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rank.interactive.app.model.Logs;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Long> {

	@Query(value = "select * from Logs l where lower(l.username) = ? order by created_date desc limit 10", nativeQuery = true)
	List<Logs> getPlayerlogs(String username);
}
