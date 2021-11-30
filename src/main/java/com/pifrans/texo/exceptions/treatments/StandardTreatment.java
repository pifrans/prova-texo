package com.pifrans.texo.exceptions.treatments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class StandardTreatment {
    private static final long serialVersionUID = 1L;
    private Integer status;
    private String message;
    private Long timeStamp;
    private String exception;
}
