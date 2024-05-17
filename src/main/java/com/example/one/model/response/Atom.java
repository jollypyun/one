package com.example.one.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atom {
        String name;
        String abbr;
        Integer photon;
        Integer neutron;
        Float mass;
        Float meltingPoint;
        Float boilingPoint;
}
