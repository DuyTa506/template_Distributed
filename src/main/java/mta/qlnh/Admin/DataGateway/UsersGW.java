package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Dishtypes;
import mta.qlnh.Admin.DO.Users;
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

public class UsersGW {
    private final WebClient webClient;
    public UsersGW()
    {
        String URI = "http://localhost:8080/sub/api/admin/users/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public List<Users> getAll()
    {
        try {
            Mono<List<Users>> response = webClient.get()
                    .uri(   "/"
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Users>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Users getById(int id)
    {
        try {
            Mono<Users> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Users>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public  int addusers(Users users)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/add")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(users) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in add user GW");
        }
        return -1;
    }

    public  int deleteuser(int id)
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
            System.out.println("Error in delete user GW");
        }
        return -1;
    }

    public  int updateUsers(Users user)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.put()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/update")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(user) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in update user GW");
        }
        return -1;
    }
}
