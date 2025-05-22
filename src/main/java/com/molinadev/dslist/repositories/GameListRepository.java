package com.molinadev.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.molinadev.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}
