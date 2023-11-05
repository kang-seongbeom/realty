package com.ssafy.realty.realty.dto.wrap;

import com.ssafy.realty.realty.dto.Home;
import lombok.Getter;

import java.util.List;

@Getter
public class Homes {
    private List<Home> homes;

    public Homes(List<Home> homes) {
        this.homes = homes;
    }
}
