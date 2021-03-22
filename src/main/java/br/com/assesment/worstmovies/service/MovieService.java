package br.com.assesment.worstmovies.service;

import br.com.assesment.worstmovies.domain.Movie;
import br.com.assesment.worstmovies.gateway.model.ProducerInterval;
import br.com.assesment.worstmovies.gateway.response.ProducerIntervalResponse;
import br.com.assesment.worstmovies.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;

    public void save(List<Movie> movies) {
        movieRepository.deleteAll();
        movies.stream().forEach(m -> {
            movieRepository.save(m);
        });
    }

    public ProducerIntervalResponse getProducerWithBiggestIntervalWinner() {
        List<Movie> winners = movieRepository.findAll()
                .stream()
                .filter(f -> f.isWinner())
                .collect(Collectors.toList());

        List<ProducerInterval> producers = new ArrayList<>();
        int previousWin = 0;
        for (Movie m : winners) {
            List<Movie> producerList = winners.stream().filter(f -> f.getProducer().equals(m.getProducer())).collect(Collectors.toList());
            for (Movie mm : producerList.stream().sorted(Comparator.comparing(c -> c.getProducer())).collect(Collectors.toList())) {
                producers.add(new ProducerInterval()
                        .builder()
                        .producer(mm.getProducer())
                        .followingWin(mm.getYear())
                        .previousWin(previousWin)
                        .interval(previousWin == 0 ? 0 : mm.getYear() - previousWin)
                        .build());
                previousWin = mm.getYear();
            }
            previousWin = 0;
        }

        producers.removeIf(f -> f.getInterval() == 0);

        if (producers.size() > 0) {
            producers = producers.stream().sorted(Comparator.comparing(c -> c.getInterval())).distinct().collect(Collectors.toList());

            return new ProducerIntervalResponse()
                    .builder()
                    .min(getWinners(producers, producers.stream().min(Comparator.comparing(ProducerInterval::getInterval)).get().getInterval()))
                    .max(getWinners(producers, producers.stream().max(Comparator.comparing(ProducerInterval::getInterval)).get().getInterval()))
                    .build();
        }

        return new ProducerIntervalResponse();
    }

    private List<ProducerInterval> getWinners(List<ProducerInterval> producers, int value) {
        return producers.stream().filter(f -> f.getInterval() == value).collect(Collectors.toList());
    }
}
