package br.com.assesment.worstmovies.repository;

import br.com.assesment.worstmovies.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
