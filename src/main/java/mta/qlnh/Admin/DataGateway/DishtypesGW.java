package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Dishtypes;
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

public class DishtypesGW {
    private final WebClient webClient;
    public DishtypesGW()
    {
        String URI = "http://localhost:8080/sub/api/admin/dishtypes/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public List<Dishtypes> getAll(String name, String description)
    {
        try {
            Mono<List<Dishtypes>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/")
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Dishtypes>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public  int addDishtypes(Dishtypes dishtypes)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/add")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(dishtypes) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in add dish type GW");
        }
        return -1;
    }

    public  int deleteDishtypes(int id)
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
            System.out.println("Error in delete dish type GW");
        }
        return -1;
    }

    public  int updateDishtypes(Dishtypes dishtypes)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.put()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/update")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(dishtypes) )
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
