package com.molinadev.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.molinadev.dslist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
