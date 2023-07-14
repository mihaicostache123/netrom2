package com.example.championship.service;

import com.example.championship.model.Player;
import com.example.championship.repository.PlayerRepository;
import com.example.championship.repository.TeamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    public PlayerService() {
    }

    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }

    public void addPlayer(Player player) {
        if (this.teamRepository.findById(player.getTeam().getId()).isPresent()) {
            this.playerRepository.save(player);
        } else {
            throw new Error("Team with id " + player.getTeam().getId() + " does not exist");
        }
    }

    public void updatePlayer(Player player) {
        if (this.teamRepository.findById(player.getTeam().getId()).isPresent()) {
            this.playerRepository.save(player);
        } else {
            throw new Error("Team with id " + player.getTeam().getId() + " does not exist");
        }
    }

    public void deletePlayer(Player player) {
        this.playerRepository.delete(player);
    }

    public void deletePlayerById(Long id) {
        this.playerRepository.deleteById(Math.toIntExact(id));
    }

    public void deleteAllPlayers() {
        this.playerRepository.deleteAll();
    }

    public Player getPlayerById(int id) {
        return (Player)this.playerRepository.findById(id).get();
    }

    public List<Player> getAllPlayersSorted(String sort) {
        List var10000;
        switch (sort.toLowerCase()) {
            case "id":
                var10000 = this.playerRepository.findAllByOrderByIdAsc();
                break;
            case "first_name":
                var10000 = this.playerRepository.findAllByOrderByNameAsc();
                break;
            case "age":
                var10000 = this.playerRepository.findAllByOrderByAgeAsc();
                break;
            case "position":
                var10000 = this.playerRepository.findAllByOrderByPositionAsc();
                break;
                case "number":
                var10000 = this.playerRepository.findAllByOrderByNumberAsc();
                break;
            default:
                var10000 = this.playerRepository.findAll();
        }

        return var10000;
    }
}
