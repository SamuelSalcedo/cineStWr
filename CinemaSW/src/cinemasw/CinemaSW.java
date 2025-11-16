package cinemasw;

import cinemasw.models.starWarsAPI;
import cinemasw.models.titulosPeliculas;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaSW {

    public static String json = "";

    public static Scanner peticion = new Scanner(System.in);

    public static Gson gson = new GsonBuilder()
            // para que la clase record tenga las variables en minusculas
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create();

    public static List<String> listaConsultas = new ArrayList<>();
    public static List<titulosPeliculas> titulos = new ArrayList<>();

    //public static String busqueda = "";
    public static void main(String[] args) throws IOException, InterruptedException {

        listaConsultas.add("films");
        listaConsultas.add("people");
        listaConsultas.add("planets");
        listaConsultas.add("species");

        //leer la opcion del usuario
        Scanner leer = new Scanner(System.in);

        //para crear lo que va a buscar en la peticion 
        while (true) {

            System.out.println("INGRESA LO QUE DESEES BUSCAR SOBRE STAR WARS");

            System.out.println("0.- Peliculas");
            System.out.println("1.- Personajes");
            System.out.println("2.- Planetas");
            System.out.println("3.- Especies alienigenas");

        
        
            
            var busqueda = leer.nextLine();
            if (busqueda.equalsIgnoreCase("salir")) {
                break;
            }
            switch (busqueda) {
                case "0":
                    pedirUrlAPI(busqueda);
                    break;
                case "1":
                    pedirUrlAPI(busqueda);
                    break;                
                case "2":
                    pedirUrlAPI(busqueda);
                    break;
                case "3":
                    pedirUrlAPI(busqueda);
                    break;
                default:
                    throw new AssertionError();
            }
        }

    }

    public static String crearUrl(String clave) {
        //ver si se trata de una pelicula, personaje, etc...
        boolean busqueda = listaConsultas.contains(clave);
        var id = "";
        //ahora un indice dependiendo del caso

        switch (clave) {
            case "0":
                System.out.println("INGRESA EL ID DE LA PELICULA PARA VER SU INFO: ");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                id = peticion.nextLine();

                break;
            case "1":
                System.out.println("INGRESA EL ID DEL PERSONAJE PARA VER SU INFO: ");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                id = peticion.nextLine();

                break;
            case "2":
                System.out.println("INGRESA EL ID DEL PLANETA PARA VER SU INFO: ");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                id = peticion.nextLine();

                break;
            case "3":
                System.out.println("INGRESA EL ID DE LA ESPECIE PARA VER SU INFO: ");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                System.out.println("1 - a new hope");
                id = peticion.nextLine();

                break;

            default:

                break;
        }

        id = URLEncoder.encode(id, StandardCharsets.UTF_8);

        if (!(busqueda)) {
            //direccion del caso que realiza la peticion
            return "https://swapi.py4e.com/api/" + listaConsultas.get(Integer.parseInt(clave)) + "/" + id + "/";
        } else {
            return "";
        }
    }

    public static void pedirUrlAPI(String buscar) throws IOException, InterruptedException {
        String direccion = crearUrl(buscar);
        System.out.println("la url: "+direccion);
        try {

            //metodo abstracto para crear la peticion http
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    //crea la url donde se realiza la peticion
                    .uri(URI.create(direccion))
                    .build();

            //creacion del string de la respuesta que se obtine de la peticion
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //vuelca el contenido de la respuesta en un json
            json = response.body();
            System.out.println(json);

            //crea una instancia de la clase record para usarla como base para el json
            starWarsAPI mitituloSW = gson.fromJson(json, starWarsAPI.class);
            
            
            System.out.println(mitituloSW);
            
            
            //en una instancia de la clase titulos que usamos para volvar la informacion de la clase record
            titulosPeliculas miTitulo = new titulosPeliculas(mitituloSW);

            System.out.println(miTitulo.toString());
            //ingresar el resultado en la lista titulos
            titulos.add(miTitulo);
        } catch (IllegalArgumentException ex) {
            System.out.println("VERIFICA LA DIRECCION");

        } catch (Exception e) {
            System.out.println("ALGO MALO PASO: " + e);
        }
    }

}
