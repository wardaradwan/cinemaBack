package com.example.cinema.Entity;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name="p1",types={com.example.cinema.Entity.Projection.class})
public interface ProjectionProj {
    public Long getId();
    public double getPrix();
    public Date getdateProjection();
    public Salle getSalle();
    public Film getFilm();
    public Seance getSeance();
    public Collection<Ticket> getTickets();
}
