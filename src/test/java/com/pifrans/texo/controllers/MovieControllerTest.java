package com.pifrans.texo.controllers;

import com.pifrans.texo.models.Movie;
import com.pifrans.texo.services.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private Integer port;

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void findByIdBodyIsNotNull() {
        Movie m1 = new Movie(1L, 2020, "Título 1", "Studios 1", "Producers 1", true);
        BDDMockito.when(movieService.findById(m1.getId())).thenReturn(m1);
        ResponseEntity<Movie> response = restTemplate.getForEntity("/movies/{id}", Movie.class, m1.getId());
        Assertions.assertTrue((response.getBody() != null));
    }

    @Test
    public void findAllBodyIsNotEmpty() {
        List<Movie> list = new ArrayList<>();
        Movie m1 = new Movie(1L, 2020, "Título 1", "Studios 1", "Producers 1", true);
        Movie m2 = new Movie(2L, 2021, "Título 2", "Studios 2", "Producers 2", true);
        Movie m3 = new Movie(3L, 2010, "Título 3", "Studios 1", "Producers 1", false);
        Movie m4 = new Movie(4L, 2003, "Título 4", "Studios 2", "Producers 2", true);
        Movie m5 = new Movie(5L, 2005, "Título 5", "Studios 1", "Producers 1", false);
        Movie m6 = new Movie(6L, 2015, "Título 6", "Studios 2", "Producers 2", true);
        list.addAll(Arrays.asList(m1, m2, m3, m4, m5, m6));

        BDDMockito.when(movieService.findAll()).thenReturn(list);

        ResponseEntity<Collection> response = restTemplate.getForEntity("/movies", Collection.class);
        Assertions.assertEquals(list.size(), response.getBody().size());
    }
}
