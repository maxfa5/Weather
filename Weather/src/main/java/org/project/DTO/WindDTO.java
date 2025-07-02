package org.project.DTO;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WindDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    double speed;
    double deg;

    public WindDTO(double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }
}
