package com.example.championship.controller;

import com.example.championship.model.Player;
import com.example.championship.service.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerservice;

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return this.playerservice.getAllPlayers();
    }

    @GetMapping("all/sort={sort}")
    public List<Player> getAllPlayersSorted(@PathVariable("sort") String sort) {
        return this.playerservice.getAllPlayersSorted(sort);
    }

    @GetMapping("/id={id}")
    public Player getPlayerById(@PathVariable("id") int id) {
        return this.playerservice.getPlayerById(id);
    }

    @PostMapping("/add")
    public void addPlayer(@RequestBody Player player) {
        this.playerservice.addPlayer(player);
    }

    @PostMapping("/update")
    public void updatePlayer(@RequestBody Player player) {
        this.playerservice.updatePlayer(player);
    }

    @DeleteMapping("/delete")
    public void deletePlayer(@RequestBody Player player) {
        this.playerservice.deletePlayer(player);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayerById(@PathVariable("id") Long id) {
        this.playerservice.deletePlayerById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllPlayers() {
        this.playerservice.deleteAllPlayers();
    }
}
