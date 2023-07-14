package com.example.championship.service;

import com.example.championship.model.Game;
import com.example.championship.model.Player;
import com.example.championship.model.Team;
import com.example.championship.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return this.teamRepository.findAll();
    }

    public void addTeam(Team team) {
        this.teamRepository.save(team);
    }

    public void updateTeam(Team team) {
        this.teamRepository.save(team);
    }

    public void deleteTeam(Team team) {
        this.teamRepository.delete(team);
    }

    public void deleteTeamById(Long id) {
        this.teamRepository.deleteById(Math.toIntExact(id));
    }

    public void deleteAllTeams() {
        this.teamRepository.deleteAll();
    }

    public Team getTeamById(int id) {
        return (Team)this.teamRepository.findById(id).get();
    }

    public List<Team> getAllTeamsSorted(String sort) {
        List var10000;
        switch (sort.toLowerCase()) {
            case "id":
                var10000 = this.teamRepository.findAllByOrderByIdAsc();
                break;
            case "name":
                var10000 = this.teamRepository.findAllByOrderByNameAsc();
                break;
            case "country":
                var10000 = this.teamRepository.findAllByOrderByCountryAsc();
                break;
            default:
                var10000 = this.teamRepository.findAll();
        }

        return var10000;
    }
}
