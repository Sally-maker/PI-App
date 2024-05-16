package com.carro.aula_pi.dto.video;

import com.carro.aula_pi.entity.Video;

public record VideoRetornoDTO(Long id, Long idUsuario, String titulo, String url, String duracao, String categoria, int visualizacoes, boolean restrito, String dataDePostagem) {

    public VideoRetornoDTO(Video video) {
        this(video.getId(), video.getIdUsuario().getId(),
                video.getTitulo(), video.getUrl(), video.getDuracao(),
                video.getCategoria(), video.getVisualizacoes(), video.isRestrito(),
                video.getDataDePostagem());
}
}
