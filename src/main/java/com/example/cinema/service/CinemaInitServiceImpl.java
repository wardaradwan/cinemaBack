package com.example.cinema.service;

import com.example.cinema.Entity.*;
import com.example.cinema.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
@Transactional
@Service
public class CinemaInitServiceImpl implements ICinemaInitService{
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public void initVilles() {
        Stream.of("Casablanca","Marrakech","Rabat","Tanger").forEach(nameVille->{
            Ville ville = new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });

    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville->{
            Stream.of("Megarama","Imax","Founon","Chahrazad","Daouliz").forEach(nameCinema->{
                Cinema cinema=new Cinema();
                cinema.setName(nameCinema);
                cinema.setVille(ville);
                cinema.setNombreSalles(3+(int)(Math.random()*7));
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat= new SimpleDateFormat("HH:mm");
        Stream.of("12:00","14:00","16:00","18:00","20:00").forEach(s ->{
            Seance seance = new Seance();
            try{
            seance.setHeureDebut(dateFormat.parse(s));
            seanceRepository.save(seance);
            }catch(ParseException e ){
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setProjection(p);
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            });

        });

    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema ->{
            for(int i=0;i<cinema.getNombreSalles();i++){
                Salle salle = new Salle();
                salle.setName("Salle"+(i+1));
                salle.setCinema(cinema);
                salle.setNombreplace(15+(int)(Math.random()*10));
                salleRepository.save(salle);
            }
        });

    }

    @Override
    public void initProjections() {
        double[] prices=new double[] {30,45,60,70,85,100};
        List<Film> films=filmRepository.findAll();
        villeRepository.findAll().forEach(ville->{
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle->{
                    int index=new Random().nextInt(films.size());
                    Film film=films.get(index);
                            seanceRepository.findAll().forEach(seance -> {
                                Projection projection =new Projection();
                                projection.setDateProjection(new Date());
                                projection.setFilm(film);
                                projection.setPrix(prices[new Random().nextInt(prices.length)]);
                                projection.setSalle(salle);
                                projection.setSeance(seance);
                                projectionRepository.save(projection);
                            });

                        });
            });
        });
    };



    @Override
    public void initFilms() {
        double[] durees=new double[] {1,2,1,3,1,2};
        List<Category> categories=categoryRepository.findAll();

        Stream.of("Cruella","Luca","TheNest","Tides").forEach(titrefilm->{
            Film film = new Film();
            film.setTitre(titrefilm);
            film.setDuree(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(titrefilm.replaceAll(" ","")+".jpg");
            film.setCategory(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);

        });

    }

    @Override
    public void initCategories() {
        Stream.of("Histoire","Action","Drama","Horreur").forEach(cat->{
            Category categorie = new Category();
            categorie.setName(cat);
            categoryRepository.save(categorie);


        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for(int i = 0 ; i< salle.getNombreplace(); i++){
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);

            }
        });
    }
}
