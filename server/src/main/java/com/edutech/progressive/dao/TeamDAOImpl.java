package com.edutech.progressive.dao;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Team;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAOImpl implements TeamDAO {
    @Override
    public int addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO team (team_name, location, owner_name, establishment_year) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, team.getTeamName());
            ps.setString(2, team.getLocation());
            ps.setString(3, team.getOwnerName());
            ps.setInt(4, team.getEstablishmentYear());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    @Override
    public Team getTeamById(int teamId) throws SQLException {
        String sql = "SELECT team_id, team_name, location, owner_name, establishment_year FROM team WHERE team_id = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapTeam(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void updateTeam(Team team) throws SQLException {
        String sql = "UPDATE team SET team_name=?, location=?, owner_name=?, establishment_year=? WHERE team_id=?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, team.getTeamName());
            ps.setString(2, team.getLocation());
            ps.setString(3, team.getOwnerName());
            ps.setInt(4, team.getEstablishmentYear());
            ps.setInt(5, team.getTeamId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteTeam(int teamId) throws SQLException {
        String sql = "DELETE FROM team WHERE team_id = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, teamId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT team_id, team_name, location, owner_name, establishment_year FROM team";
        try (Connection conn = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                teams.add(mapTeam(rs));
            }
        }
        return teams;
    }

    private Team mapTeam(ResultSet rs) throws SQLException {
        Team team = new Team();
        team.setTeamId(rs.getInt("team_id"));
        team.setTeamName(rs.getString("team_name"));
        team.setLocation(rs.getString("location"));
        team.setOwnerName(rs.getString("owner_name"));
        team.setEstablishmentYear(rs.getInt("establishment_year"));
        return team;
    }
}
