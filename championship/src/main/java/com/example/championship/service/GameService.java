package com.example.championship.service;

import com.example.championship.model.Game;
import com.example.championship.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public void addGame(Game game) {
        if(!gameRepository.findById(game.getTeam1().getId()).isPresent()) {
            throw new Error("Team1 with id " + game.getId() + " does not exist");
        }
        else if(!gameRepository.findById(game.getTeam2().getId()).isPresent()) {
            throw new Error("Team2 with id " + game.getId() + " does not exist");
        }
        else {
            gameRepository.save(game);
        }
    }
    public void updateGame(Game game) {
        if(gameRepository.findById(game.getId()).isPresent()){
            gameRepository.save(game);
        }
        else {
            throw new Error("Game with id " + game.getId() + " does not exist");
        }
    }
    public void deleteGame(Game game) {
            gameRepository.delete(game);
    }
    public void deleteGameById(Long id) {
        this.gameRepository.deleteById(Math.toIntExact(id));
    }

    public void deleteAllGames() {
        this.gameRepository.deleteAll();
    }

    public Game getGameById(int id) {
        return (Game)this.gameRepository.findById(id).get();
    }
    public List<Game> getAllGamesSorted(String sort) {
        List var10000;
        switch (sort.toLowerCase()) {
            case "id":
                var10000 = this.gameRepository.findAllByOrderByIdAsc();
                break;
            case "team1":
                var10000 = this.gameRepository.findAllByOrderByTeam1Asc();
                break;
            case "team2":
                var10000 = this.gameRepository.findAllByOrderByTeam2Asc();
                break;
            case "score1":
                var10000 = this.gameRepository.findAllByOrderByScore1Asc();
                break;
            case "score2":
                var10000 = this.gameRepository.findAllByOrderByScore2Asc();
                break;
            default:
                var10000 = this.gameRepository.findAll();
        }

        return var10000;
    }
}
