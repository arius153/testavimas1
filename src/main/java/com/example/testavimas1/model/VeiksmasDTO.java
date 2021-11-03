package com.example.testavimas1.model;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class VeiksmasDTO {
    private Long id;
    private String veiksmas;
    private LocalDate data;
    private Long vartotojoId;

    public VeiksmasDTO(String veiksmas, LocalDate data, Long vartotojoId) {
        this.veiksmas = veiksmas;
        this.data = data;
        this.vartotojoId = vartotojoId;
    }
}
