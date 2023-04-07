package com.example.one.serviceimpl;

import com.example.one.model.entity.Nationality;
import com.example.one.model.response.OneNationResponse;
import com.example.one.repository.NationalityRepository;
import com.example.one.service.NationalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NationalityServiceImpl implements NationalityService {
    private final NationalityRepository nationalityRepository;

    @Override
    public OneNationResponse selectRandomNation() {
        List<Nationality> list = nationalityRepository.findAll();
        Collections.shuffle(list);
        Nationality nation = list.stream().findFirst().isEmpty() ? null : list.stream().findFirst().get();
        assert nation != null;
        return OneNationResponse.builder()
                .name(nation.getName())
                .capital(nation.getCapital())
                .build();
    }
}
