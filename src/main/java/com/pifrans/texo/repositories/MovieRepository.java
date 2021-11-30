package com.pifrans.texo.repositories;

import com.pifrans.texo.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m.producers FROM Movie m GROUP BY m.producers HAVING COUNT(m) > 1 ORDER BY m.producers")
    List<String> findRepeatProducers();

    @Query(value = "SELECT *\n" +
            "FROM (SELECT m.id,\n" +
            "             m.producers,\n" +
            "             m.year                                                               AS previous_win,\n" +
            "             MIN(m.year) OVER (ROWS BETWEEN 1 FOLLOWING AND 1 FOLLOWING)          AS following_win,\n" +
            "             MIN(m.year) OVER (ROWS BETWEEN 1 FOLLOWING AND 1 FOLLOWING) - m.year AS period,\n" +
            "             MIN(m.id) OVER (ROWS BETWEEN 1 FOLLOWING AND 1 FOLLOWING)            AS id_next\n" +
            "      FROM (SELECT * FROM movies m WHERE m.producers = ?1) m) t\n" +
            "WHERE t.period IS NOT NULL;", nativeQuery = true)
    List<Object> findDataByProducers(String producers);
}
