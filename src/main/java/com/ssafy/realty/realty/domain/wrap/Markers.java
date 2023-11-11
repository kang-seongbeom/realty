package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.Marker;
import lombok.Getter;

import java.util.List;

@Getter
public class Markers {
    private final List<Marker> markers;

    public Markers(List<Marker> marker){
        this.markers = marker;
    }
}
