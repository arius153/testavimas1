package com.example.testavimas1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.aspectj.weaver.ast.Var;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Vartotojas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vardas;
    private String telNr;
    @JsonIgnore
    @OneToMany(mappedBy = "vartotojas", cascade = CascadeType.ALL)
    private List<Veiksmas> veiksmai;

    public Vartotojas(String vardas, String telNr) {
        this.vardas = vardas;
        this.telNr = telNr;
    }

    public int compareTo(Vartotojas o) {
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
        Vartotojas other = (Vartotojas) obj;
        if (this.id != other.id)
            return false;
        return true;
    }
}
