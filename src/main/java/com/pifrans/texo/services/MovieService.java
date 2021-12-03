package com.pifrans.texo.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.pifrans.texo.dtos.DataByProducersDTO;
import com.pifrans.texo.models.Movie;
import com.pifrans.texo.repositories.MovieRepository;
import com.pifrans.texo.utilities.ResultSetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void saveFromCsv(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CsvToBean<Movie> csvToBean = new CsvToBeanBuilder<Movie>(reader).withType(Movie.class).withSeparator(';').build();
        List<Movie> movies = csvToBean.parse();
        movieRepository.saveAll(movies);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Filme de ID (" + id + "), não encontrado!"));
    }

    public List<DataByProducersDTO> findDataByProducers() {
        List<String> list = movieRepository.findRepeatProducers();
        List<DataByProducersDTO> dtos = new ArrayList<>();

        for (String producers : list) {
            List<Object> objects = movieRepository.findDataByProducers(producers);
            for (Object object : objects) {
                Object[] array = (Object[]) object;

                DataByProducersDTO dto = new DataByProducersDTO();
                dto.setIdPrevious(ResultSetUtil.toLong(array[0]));
                dto.setProducers(ResultSetUtil.toString(array[1]));
                dto.setPreviousWin(ResultSetUtil.toInteger(array[2]));
                dto.setFollowingWin(ResultSetUtil.toInteger(array[3]));
                dto.setPeriod(ResultSetUtil.toInteger(array[4]));
                dto.setIdFollowing(ResultSetUtil.toLong(array[5]));
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public List<DataByProducersDTO> getProductionPeriodBetweenTwoAwards(Integer period) throws NoSuchElementException {
        List<DataByProducersDTO> dtos = findDataByProducers();

        if (dtos.size() < 1) {
            throw new RuntimeException("É necessário ter no mínimo 2 filmes cadastrados!");
        }

        dtos = dtos.stream().filter(o -> o.getPeriod().equals(period)).collect(Collectors.toList());
        DataByProducersDTO dto = dtos.stream().findFirst().orElseThrow(() -> new NoSuchElementException("Não foi encontrado filmes de um mesmo produtor com intervalo igual a (" + period + ")!"));

        return new ArrayList<>(List.of(dto));
    }

    public Movie update(Movie movie) throws PersistenceException {
        String message = String.format("Erro ao atualizar filme de ID (%d), não encontrado!", movie.getId());
        movieRepository.findById(movie.getId()).orElseThrow(() -> new NoSuchElementException(message));
        return movieRepository.save(movie);

    }

    public Movie delete(Long id) {
        String message = String.format("Erro ao excluir filme de ID (%d), não encontrado!", id);
        Movie object = movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException(message));
        movieRepository.deleteById(object.getId());
        return object;
    }
}
