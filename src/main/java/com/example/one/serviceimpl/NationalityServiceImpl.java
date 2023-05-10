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

import java.util.List;

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
                .name(request.name())
                .capital(request.capital())
                .nationalCode(request.nationalCode())
                .isd(request.isd())
                .build();
        nationalityRepository.save(nationality);
        return OneNationResponse.builder()
                .name(nationality.getName())
                .capital(nationality.getCapital())
                .isd(nationality.getIsd())
                .nationalCode(nationality.getNationalCode())
                .build();
    }

    @Override
    public OneNationResponse updateOneNation(OneNationRequest request) {
        Nationality nationality = nationalityRepository.findByName(request.name());
        Nationality updated = Nationality.builder()
                .nationId(nationality.getNationId())
                .name(request.name())
                .capital(request.capital())
                .nationalCode(request.nationalCode())
                .isd(request.isd())
                .build();
        nationalityRepository.save(updated);
        return OneNationResponse.builder()
                .name(updated.getName())
                .capital(updated.getCapital())
                .nationalCode(updated.getNationalCode())
                .isd(updated.getIsd())
                .build();
    }

    @Override
    public Integer deleteNations(List<String> request) {
        List<String> ids  = request.stream().map(nation ->
            nationalityRepository.findByName(nation).getNationId()
        ).toList();
        nationalityRepository.deleteAllByIdInBatch(ids);
        return request.size();
    }
}
