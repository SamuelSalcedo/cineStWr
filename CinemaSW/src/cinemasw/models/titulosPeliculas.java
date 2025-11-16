package cinemasw.models;

import java.util.List;


public class titulosPeliculas {
    String title;
    String director;
    List<String> characters;
    List<String> planets;
    List<String> starships;
    List<String> species;

    public titulosPeliculas(String title, String director, List<String> characters, List<String> planets, List<String> starships, List<String> species) {
        this.title = title;
        this.director = director;
        this.characters = characters;
        this.planets = planets;
        this.starships = starships;
        this.species = species;
    }

    public titulosPeliculas(starWarsAPI starWars){
        this.title = starWars.title();
        this.director = starWars.director();
        this.characters = starWars.characters();
        this.planets = starWars.planets();
        this.starships = starWars.starships();
        this.species = starWars.species();
    }
    
    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public List<String> getStarships() {
        return starships;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }
    
    
}
