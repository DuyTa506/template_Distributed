package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.DO.Invoices;
import mta.qlnh.Admin.DTO.Invoice_details_K;
import mta.qlnh.Admin.DTO.Invoices_detailsDTO;
import mta.qlnh.Admin.DTO.OrderObjectRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class KitchenGW {
    private final WebClient webClient;
    public KitchenGW()
    {
        String URI = "http://localhost:8080/sub/api/admin/kitchen/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public List<Invoice_details_K> getAll()
    {
        try {
            Mono<List<Invoice_details_K>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/")
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Invoice_details_K>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public List< Invoice_details_K> getByID(int id)
    {
        try {
            Mono<List< Invoice_details_K>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/byId/")
                            .queryParam("dishtype_id", id)
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List< Invoice_details_K>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Invoices_detailsDTO acceptedOrder(int id)
    {
        try {
            Mono<Invoices_detailsDTO> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/accepted/{order_detail_id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Invoices_detailsDTO>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Invoices_detailsDTO cooked(OrderObjectRequest obj)
    {
        try {
            AtomicInteger return_status = new AtomicInteger(404);
            Mono<Invoices_detailsDTO>  response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/cooked")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(obj) )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Invoices_detailsDTO>() {});
            return response.block();

        }catch (Exception e)
        {
            System.out.println("Error in add permission group GW");
        }
        return null;
    }

    public  Invoices_detailsDTO ordered(OrderObjectRequest obj)
    {
        try {
            AtomicInteger return_status = new AtomicInteger(404);
            Mono<Invoices_detailsDTO>  response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/ordered")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(obj) )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Invoices_detailsDTO>() {});
            return response.block();

        }catch (Exception e)
        {
            System.out.println("Error in add permission group GW");
        }
        return null;
    }


}
