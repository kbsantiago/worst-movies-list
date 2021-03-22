package br.com.assesment.worstmovies.gateway.http;

import br.com.assesment.worstmovies.gateway.response.ProducerIntervalResponse;
import br.com.assesment.worstmovies.service.MovieService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(name = "/movie")
@AllArgsConstructor
public class MovieController {
    @Autowired
    private MovieService movieService;

    @ApiOperation(value = "Return Producer with biggest and lowest interval winners")
    @RequestMapping(path="/winners", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getProducerWithBiggestAndLowestIntervalWinner() {
        ProducerIntervalResponse producerIntervalResponse = movieService.getProducerWithBiggestIntervalWinner();
        if(producerIntervalResponse.getMax() == null && producerIntervalResponse.getMin() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(movieService.getProducerWithBiggestIntervalWinner());
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getProducerWithBiggestIntervalWinner());
    }
}
