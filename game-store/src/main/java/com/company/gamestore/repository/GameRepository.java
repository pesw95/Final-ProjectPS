package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    // specific queries
    List<Game> findAllGamesByStudio(String studio);
    List<Game> findAllGamesByEsrbRating(String esrb);
    List<Game> findGameByTitle(String title);
}
