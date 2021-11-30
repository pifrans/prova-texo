package com.pifrans.texo.exceptions.treatments;

import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class HandlerTreatment {
    private static final Logger LOG = LoggerFactory.getLogger(HandlerTreatment.class);

    @ExceptionHandler(CsvRequiredFieldEmptyException.class)
    public ResponseEntity<StandardTreatment> csvRequiredFieldEmpty(CsvRequiredFieldEmptyException exception) {
        StandardTreatment error = new StandardTreatment(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), System.currentTimeMillis(), CsvRequiredFieldEmptyException.class.getName());
        LOG.error(error.toString());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardTreatment> runtime(RuntimeException exception) {
        StandardTreatment error = new StandardTreatment(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), System.currentTimeMillis(), RuntimeException.class.getName());
        LOG.error(error.toString());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardTreatment> noSuchElement(NoSuchElementException exception) {
        StandardTreatment error = new StandardTreatment(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis(), NoSuchElementException.class.getName());
        LOG.error(error.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<StandardTreatment> iOException(IOException exception) {
        StandardTreatment error = new StandardTreatment(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), System.currentTimeMillis(), IOException.class.getName());
        LOG.error(error.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
