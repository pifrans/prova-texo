package com.pifrans.texo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DataByProducersDTO implements Comparator<DataByProducersDTO> {
    @JsonIgnore
    private Long idPrevious;

    @JsonIgnore
    private Long idFollowing;

    @JsonProperty("producer")
    private String producers;

    private Integer previousWin;
    private Integer followingWin;

    @JsonProperty("interval")
    private Integer period;

    @Override
    public int compare(DataByProducersDTO o1, DataByProducersDTO o2) {
        return Integer.compare(o1.getPeriod(), o2.getPeriod());
    }
}
