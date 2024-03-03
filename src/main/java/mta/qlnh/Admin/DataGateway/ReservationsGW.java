package mta.qlnh.Admin.DataGateway;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.DTO.ReservationsDTO;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReservationsGW {

    private final WebClient webClient;

    public ReservationsGW ()
    {
        String URI = "http://localhost:8080/sub/api/admin/reservations/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public List<ReservationsDTO> getAll (){
        try {

            Mono<List<ReservationsDTO>> response = webClient.get()
                    .uri("/")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<ReservationsDTO>>() {});
            return response.block();
        }catch (Exception e)
        {
            System.out.println("Error in Reservation GW");
        }
        return new ArrayList<>();
    }

    public Reservations getById(int id){
        try {

            Mono<Reservations> response = webClient.get()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/{id}")
                                    .build(id)
                            )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Reservations>() {});
            return response.block();
        }catch (Exception e)
        {
            System.out.println("Error in Reservation GW");
        }
        return new Reservations();
    }


    public  int addReservations(Reservations reservations)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/add")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(reservations) )
                    .retrieve();

            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
    {
        System.out.println("Error in Reservation GW");
    }
        return -1;
    }

    public  int deleteReservations(int id)
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
            System.out.println("Error in Reservation GW");
        }
        return -1;
    }

    public int updateReservations(Reservations reservations)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.put()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/update")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(reservations) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in Reservation GW");
        }
        return -1;
    }

}
