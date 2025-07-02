package org.project.DTO;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class MainInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    String temp;
    String pressure;
    String humidity;
    String temp_min;
    String temp_max;

    public MainInfoDTO(String temp, String pressure, String humidity, String temp_min, String temp_max) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }
}
