package com.example.cinema.dao;

import com.example.cinema.Entity.Cinema;
import com.example.cinema.Entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource @CrossOrigin("*")
public interface VilleRepository extends JpaRepository<Ville,Long> {
}
