package com.example.cinema.dao;

import com.example.cinema.Entity.Cinema;
import com.example.cinema.Entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource @CrossOrigin("*")
public interface FilmRepository extends JpaRepository<Film,Long> {
}
