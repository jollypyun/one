package com.example.one.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class TradeData {
    private String country_eng_nm;
    private String country_iso_alp2;
    private String country_nm;
    private String export_cn;
    private String income_cn;
    private Long yt_export_amount;
    private Long yt_income_amount;
    private String yt_trade_year;
}
