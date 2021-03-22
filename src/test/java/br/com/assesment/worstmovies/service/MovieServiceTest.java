package br.com.assesment.worstmovies.service;

import br.com.assesment.worstmovies.WorstMoviesApplicationTests;
import br.com.assesment.worstmovies.domain.Movie;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

public class MovieServiceTest extends WorstMoviesApplicationTests {

    private MockMvc mockMvc;

    private List<Movie> movies;

    @Autowired
    private MovieService movieService;

    @Before
    public void setUp() {
        movies = new ArrayList<>();
        movies.add(Movie.builder().year(1980).title("Movie test").producer("Producer 1").studios("Studios name").winner(true).build());
        movies.add(Movie.builder().year(1981).title("Movie test 1").producer("Producer 1").studios("Studios name").winner(false).build());
        movies.add(Movie.builder().year(1982).title("Movie test 2").producer("Producer 1").studios("Studios name").winner(true).build());
        movies.add(Movie.builder().year(1983).title("Movie test 3").producer("Producer 1").studios("Studios name").winner(false).build());
        movies.add(Movie.builder().year(1984).title("Movie test 4").producer("Producer 1").studios("Studios name").winner(false).build());
        movies.add(Movie.builder().year(1980).title("Movie test 5").producer("Producer 1").studios("Studios name").winner(false).build());

        this.mockMvc = MockMvcBuilders.standaloneSetup(movieService).build();
    }

    @Test
    public void save_movies() {
        movieService.save(movies);
    }

    @Test
    public void get_movies_success() {

    }


}
