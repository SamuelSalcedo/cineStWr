
package cinemasw.models;

import java.util.List;


public record starWarsAPI(String title, String director, List<String> characters, List<String> planets, List<String> starships, List<String> species) {
    
}
