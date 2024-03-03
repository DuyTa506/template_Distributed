package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.DTO.Permissions_cli;
import mta.qlnh.Admin.DTO.ReservationsDTO;
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

public class PermissionsGW {
    private final WebClient webClient;
    public PermissionsGW()
    {
        String URI = "http://localhost:8080/sub/api/admin/permissions/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public List<Permissions_cli> getAll(String name, String description)
    {
        try {
            Mono<List<Permissions_cli>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/")
                            .queryParam("name", name)
                            .queryParam("description", description)
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Permissions_cli>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Permissions_cli getById(int id)
    {
        try {
            Mono<Permissions_cli> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Permissions_cli>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public  int addPermissions(Permissions_cli permissions_cli)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(permissions_cli) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in add permission GW");
        }
        return -1;
    }

    public  int deletePermissions(int id)
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

    public  int updatePermissions(Permissions_cli permissions_cli)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.put()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(permissions_cli) )
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
