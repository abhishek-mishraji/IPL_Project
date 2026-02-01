package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.repository.TeamRepository;
import com.edutech.progressive.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImplJpa implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> getAllTeams() throws SQLException {
        return teamRepository.findAll();
    }

    @Override
    public int addTeam(Team team) throws SQLException {
        Team saved = teamRepository.save(team);
        return saved.getTeamId();
    }

    @Override
    public List<Team> getAllTeamsSortedByName() throws SQLException {
        return teamRepository.findAll(Sort.by(Sort.Direction.ASC, "teamName"));
    }

    @Override
    public Team getTeamById(int teamId) throws SQLException {
        Optional<Team> opt = teamRepository.findById(teamId);
        return opt.orElse(null);
    }

    @Override
    public void updateTeam(Team team) throws SQLException {
        teamRepository.save(team);
    }

    @Override
    public void deleteTeam(int teamId) throws SQLException {
        teamRepository.deleteById(teamId);
    }
}