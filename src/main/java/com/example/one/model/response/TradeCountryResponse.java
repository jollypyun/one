package com.example.one.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class TradeCountryResponse {
    private Integer currentCount;
    private List<TradeData> data;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer resultCode;
    private String resultMsg;
    private Integer totalCount;
}
