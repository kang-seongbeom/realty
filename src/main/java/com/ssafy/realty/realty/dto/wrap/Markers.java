package com.ssafy.realty.realty.dto.wrap;

import com.ssafy.realty.realty.dto.Marker;
import lombok.Getter;

import java.util.List;

@Getter
public class Markers {
    private final List<Marker> markers;

    public Markers(List<Marker> marker){
        this.markers = marker;
    }
}
