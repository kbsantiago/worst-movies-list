package br.com.assesment.worstmovies.gateway.http;

import br.com.assesment.worstmovies.WorstMoviesApplicationTests;
import br.com.assesment.worstmovies.domain.Movie;
import br.com.assesment.worstmovies.gateway.response.ProducerIntervalResponse;
import br.com.assesment.worstmovies.service.MovieService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MovieControllerTest extends WorstMoviesApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private MovieController movieController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void get_winners_success() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/winners")).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
