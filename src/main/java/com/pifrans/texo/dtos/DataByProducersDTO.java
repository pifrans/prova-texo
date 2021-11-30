package com.pifrans.texo.dtos;

import lombok.*;

import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DataByProducersDTO implements Comparator<DataByProducersDTO> {
    private Long idPrevious;
    private Long idFollowing;
    private String producers;
    private Integer previousWin;
    private Integer followingWin;
    private Integer period;

    @Override
    public int compare(DataByProducersDTO o1, DataByProducersDTO o2) {
        return Integer.compare(o1.getPeriod(), o2.getPeriod());
    }
}
