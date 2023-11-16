package com.ssafy.realty.realty.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UpdatePayload {

    @Size(min = 2)
    String title;

    List<MarkerPayload> markers;
}
