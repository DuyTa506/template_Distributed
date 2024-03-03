package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Dishes;
import mta.qlnh.Admin.DTO.DishesDTO;
import mta.qlnh.Admin.DTO.Permissions_cli;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class DishesGW {
    private final WebClient webClient;
    public DishesGW()
    {
        String URI = "http://localhost:8080/sub/api/admin/dishes/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public List<DishesDTO> getAll()
    {
        try {
            Mono<List<DishesDTO>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/")
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<DishesDTO>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Dishes getById(int id)
    {
        try {
            Mono<Dishes> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/getbyid/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Dishes>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
    public List<Dishes> searchDishes(String keyword)
    {
        try {
            Mono<List<Dishes>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/search/")
                            .queryParam("name", keyword)
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Dishes>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }


    public List<Dishes> getDishesofinvoice(int id)
    {
        try {
            Mono<List<Dishes>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/dishesbyinvoices/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Dishes>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }



    public  int addDishes(Dishes dishes)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/add")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(dishes) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in add dishes GW");
        }
        return -1;
    }

    public  int deleteDishes(int id)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.delete()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/delete/{id}")
                                    .build(id)
                    )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in delete permission GW");
        }
        return -1;
    }

    public  int updateDishes(Dishes dishes)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.put()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(dishes) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in update permission GW");
        }
        return -1;
    }
}
