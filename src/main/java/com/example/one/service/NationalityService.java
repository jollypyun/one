package com.example.one.service;

import com.example.one.model.request.OneNationRequest;
import com.example.one.model.response.OneNationResponse;

import java.util.List;

public interface NationalityService {
    List<OneNationResponse> selectOneNation();

    OneNationResponse insertOneNation(OneNationRequest request);
}
