package org.project.DTO;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String city;
    private CordDTO coord;
    private List<WeatherDTO> weather;
    private WindDTO wind;
    private MainInfoDTO main;
        
    public WeatherDataDTO(String city, CordDTO coord, List<WeatherDTO> weather, WindDTO wind, MainInfoDTO main) {
        this.city = city;
        this.coord = coord;
        this.weather = weather;
        this.wind = wind;
        this.main = main;
    }

    
    
}
