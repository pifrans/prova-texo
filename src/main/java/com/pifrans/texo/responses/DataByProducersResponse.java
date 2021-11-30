package com.pifrans.texo.responses;

import com.pifrans.texo.dtos.DataByProducersDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataByProducersResponse {
    private List<DataByProducersDTO> min;
    private List<DataByProducersDTO> max;
}
