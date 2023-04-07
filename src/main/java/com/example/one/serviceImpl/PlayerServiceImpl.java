package com.example.one.serviceImpl;

import com.example.one.model.entity.Player;
import com.example.one.model.response.PlayersResponse;
import com.example.one.repository.PlayerRepository;
import com.example.one.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public PlayersResponse selectAllPlayer() {
        List<Player> players = playerRepository.findAll();
        return PlayersResponse.builder().players(players).build();
    }
}
