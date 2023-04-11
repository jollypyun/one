package com.example.one.serviceimpl;

import com.example.one.model.entity.Nationality;
import com.example.one.model.request.OneNationRequest;
import com.example.one.model.response.OneNationResponse;
import com.example.one.repository.NationalityRepository;
import com.example.one.service.NationalityService;
import com.example.one.support.common.GenerateId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NationalityServiceImpl implements NationalityService {
    private final NationalityRepository nationalityRepository;


    @Override
    @Transactional(readOnly = true)
    public List<OneNationResponse> selectOneNation() {
        List<Nationality> list = nationalityRepository.findAll();
        return list.stream().map(nation -> OneNationResponse.builder()
                .name(nation.getName())
                .capital(nation.getCapital())
                .nationalCode(nation.getNationalCode())
                .isd(nation.getIsd())
                .build())
                .toList();
    }

    @Override
    public OneNationResponse insertOneNation(OneNationRequest request) {
        Nationality nationality = Nationality.builder()
                .nationId(GenerateId.generateUuid())
                .name(request.getName())
                .capital(request.getCapital())
                .nationalCode(request.getNationalCode())
                .isd(request.getIsd())
                .build();
        nationalityRepository.save(nationality);
        return OneNationResponse.builder()
                .name(nationality.getName())
                .capital(nationality.getCapital())
                .isd(nationality.getIsd())
                .nationalCode(nationality.getNationalCode())
                .build();
    }
}
