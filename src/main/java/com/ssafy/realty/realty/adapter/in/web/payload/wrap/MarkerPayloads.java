package com.ssafy.realty.realty.adapter.in.web.payload.wrap;

import com.ssafy.realty.realty.adapter.in.web.payload.MarkerPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkerPayloads {
    @Size(min = 1)
    List<MarkerPayload> markers;
}
