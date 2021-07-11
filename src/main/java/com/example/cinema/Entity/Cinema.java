package com.example.cinema.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cinema implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private  String name;
    private  double longitude,latitude,altitude;
    private  int nombreSalles;
    @OneToMany(mappedBy="cinema")
    private Collection<Salle> salles;
    @ManyToOne
    private Ville ville;

}
