package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.DO.Permission_group_permissions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Permission_group_permissionsGW {
    private final WebClient webClient;
    public Permission_group_permissionsGW()
    {
        String URI = "http://localhost:8080/sub/api/admin/permissionToGroups/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public int addPermissionsToGroup(List<Permission_group_permissions> ps)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/add")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(ps) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in add permission group GW");
        }
        return -1;
    }

    public  int delPermissionsToGroup(List<Permission_group_permissions> ps)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/delete")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(ps) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in add permission group GW");
        }
        return -1;
    }

}
