package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Match;
import com.edutech.progressive.repository.MatchRepository;
import com.edutech.progressive.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImplJpa implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getAllMatches() throws SQLException {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(int matchId) throws SQLException {
        Optional<Match> opt = matchRepository.findById(matchId);
        return opt.orElse(null);
    }

    @Override
    public Integer addMatch(Match match) throws SQLException {
        Match saved = matchRepository.save(match);
        return saved.getMatchId();
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        matchRepository.save(match);
    }

    @Override
    public void deleteMatch(int matchId) throws SQLException {
        matchRepository.deleteById(matchId);
    }

    @Override
    public List<Match> getAllMatchesByStatus(String status) throws SQLException {
        return matchRepository.findByStatus(status);
    }
}