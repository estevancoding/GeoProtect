package br.com.jordan.geoprotect.geoprotect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para encapsular uma resposta de status com uma mensagem descritiva.
 */
@Getter
@Setter
@AllArgsConstructor
public class StatusDTO {

    private boolean inRiskArea;
    private String message;
}
