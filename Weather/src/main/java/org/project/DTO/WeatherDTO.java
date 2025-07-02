package org.project.DTO;

import java.io.Serializable;

import lombok.Data;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WeatherDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String main;
    String description;
    String icon;

    public WeatherDTO(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }
}
