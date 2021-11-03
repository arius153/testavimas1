package com.example.testavimas1.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Veiksmas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String veiksmas;
    private LocalDate data;
    @ManyToOne
    private Vartotojas vartotojas;

    @Override
    public String toString() {
        return id + " " + veiksmas + " " + data;
    }

    public int compareTo(Veiksmas o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Veiksmas other = (Veiksmas) obj;
        if (this.id != other.id)
            return false;
        return true;
    }
}
