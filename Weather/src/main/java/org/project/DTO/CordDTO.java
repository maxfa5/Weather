package org.project.DTO;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String lat;
    private String lon;

    public CordDTO(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }
    
    
}
