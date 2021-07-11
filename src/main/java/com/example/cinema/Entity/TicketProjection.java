package com.example.cinema.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Projection(name="ticketProj",types=Ticket.class)
public interface TicketProjection {
    public long getId();

    public String getNomClient();

    public double getPrix();

    public Integer getCodePaiement();

    public boolean getReserve();

    public Place getplace();

}

