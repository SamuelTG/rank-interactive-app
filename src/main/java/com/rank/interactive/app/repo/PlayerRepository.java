package com.rank.interactive.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rank.interactive.app.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

}
