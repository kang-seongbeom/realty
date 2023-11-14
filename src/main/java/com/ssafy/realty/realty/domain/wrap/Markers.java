package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.Marker;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
public class Markers {
    List<Marker> markers;
}
