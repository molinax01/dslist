package com.molinadev.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.molinadev.dslist.dto.GameListDTO;
import com.molinadev.dslist.entities.GameList;
import com.molinadev.dslist.projections.GameMinProjection;
import com.molinadev.dslist.repositories.GameListRepository;
import com.molinadev.dslist.repositories.GameRepository;

@Service
public class GameListService {
	@Autowired
	private GameListRepository gameListRepository;
	

	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		List<GameList>result = gameListRepository.findAll();
		List<GameListDTO> dto =result.stream().map(x-> new GameListDTO(x)).toList();
		return dto;
		
	}
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection>list = gameRepository.searchByList(listId); 
		
		GameMinProjection obj= list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for (int i = min; i <=max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}
