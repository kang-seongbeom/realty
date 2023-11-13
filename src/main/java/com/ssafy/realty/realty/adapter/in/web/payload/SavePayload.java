package com.ssafy.realty.realty.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SavePayload {
    @NotNull
    String title;
    List<MarkerPayload> markers;
}
