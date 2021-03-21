package br.com.assesment.worstmovies.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerInterval {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
