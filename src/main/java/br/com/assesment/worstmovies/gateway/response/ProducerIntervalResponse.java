package br.com.assesment.worstmovies.gateway.response;

import br.com.assesment.worstmovies.gateway.model.ProducerInterval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerIntervalResponse {
    private List<ProducerInterval> min;
    private List<ProducerInterval> max;
}
