package br.com.jordan.geoprotect.geoprotect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatusDTO {
    private boolean inRiskArea;
    private String message;
}