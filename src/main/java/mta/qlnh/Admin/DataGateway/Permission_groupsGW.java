package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DTO.Permission_groups_cli;
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

public class Permission_groupsGW {
    private final WebClient webClient;
    public Permission_groupsGW()
    {
        String URI = "http://localhost:8080/sub/api/admin/permissionGroups/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public List<Permission_groups_cli> getAll(String name, String description, String action, String status)
    {
        try {
            Mono<List<Permission_groups_cli>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/")
                            .queryParam("name", name)
                            .queryParam("description", description)
                            .queryParam("action", action)
                            .queryParam("status", status)
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Permission_groups_cli>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Permission_groups_cli getById(int id)
    {
        try {
            Mono<Permission_groups_cli> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Permission_groups_cli>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public List<Permissions_cli> GetPermissionsOfGroup(int id)
    {
        try {
            Mono<List<Permissions_cli>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/getPermissions/{id}")
                            .build(id)
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

    public  int addPermission_groups(Permission_groups_cli permission_groups_cli)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(permission_groups_cli) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in add permission group GW");
        }
        return -1;
    }

    public  int deletePermission_groups(int id)
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

    public  int updatePermission_groups(Permission_groups_cli permission_groups_cli)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.put()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/update")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(permission_groups_cli) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in update permission group GW");
        }
        return -1;
    }

}
