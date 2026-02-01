package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.impl.TeamServiceImplArraylist;
import com.edutech.progressive.service.impl.TeamServiceImplJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamServiceImplJpa teamServiceJpa;

    private final TeamServiceImplArraylist teamArrayListService = new TeamServiceImplArraylist();

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        try {
            return ResponseEntity.ok(teamServiceJpa.getAllTeams());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable int teamId) {
        try {
            Team team = teamServiceJpa.getTeamById(teamId);
            return team != null ? ResponseEntity.ok(team) : ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addTeam(@RequestBody Team team) {
        try {
            int id = teamServiceJpa.addTeam(team);
            return ResponseEntity.ok(id);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Void> updateTeam(@PathVariable int teamId, @RequestBody Team team) {
        try {
            team.setTeamId(teamId);
            teamServiceJpa.updateTeam(team);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int teamId) {
        try {
            teamServiceJpa.deleteTeam(teamId);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Team>> getAllTeamsFromArrayList() {
        return ResponseEntity.ok(teamArrayListService.getAllTeams());
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addTeamToArrayList(@RequestBody Team team) {
        return ResponseEntity.ok(teamArrayListService.addTeam(team));
    }

    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Team>> getAllTeamsSortedByNameFromArrayList() {
        return ResponseEntity.ok(teamArrayListService.getAllTeamsSortedByName());
    }
}
