package com.pifrans.texo.controllers;

import com.pifrans.texo.models.Movie;
import com.pifrans.texo.responses.DataByProducersResponse;
import com.pifrans.texo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Movie object = movieService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(object);
    }


    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Movie> list = movieService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/awardsMinMax")
    public ResponseEntity<?> findDataByProducersMinAndMaxPeriod() {
        DataByProducersResponse object = new DataByProducersResponse();
        object.setMin(movieService.getShorterProductionPeriodBetweenTwoAwards());
        object.setMax(movieService.getLongerProductionPeriodBetweenTwoAwards());

        return ResponseEntity.status(HttpStatus.OK).body(object);
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Movie movie) {
        Movie object = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(object);
    }

    @PostMapping("/csv")
    public ResponseEntity<?> saveFromCsv(@RequestParam("file") MultipartFile file) throws IOException {
        movieService.saveFromCsv(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Movie movie) {
        Movie object = movieService.update(movie);
        return ResponseEntity.status(HttpStatus.OK).body(object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Movie object = movieService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(object);
    }
}
