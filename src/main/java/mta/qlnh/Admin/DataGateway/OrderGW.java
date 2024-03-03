package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.DTO.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderGW {
    private final WebClient webClient;
    public OrderGW()
    {
        String URI = "http://localhost:8080/sub/api/admin/order/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public  int newOrder(NewOrderRequest orderRequest, String token)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/newOrder")
                                    .build()
                    )
                    .header("Authorization", token)
                    .body(BodyInserters.fromValue(orderRequest) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in add permission GW");
        }
        return -1;
    }

    public  OrderRequest order(OrderRequest orderRequest)
    {
        try {

            Mono<OrderRequest> response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/addOrder")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(orderRequest) )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<OrderRequest>() {});
            return response.block();

        }catch (Exception e)
        {
            System.out.println("Error in add permission GW");
        }
        return orderRequest;
    }

    public int cancelOrder(Request1Param req)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/cancelOrder")
                                    .build()
                    )

                    .body(BodyInserters.fromValue(req) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in delete permission GW");
        }
        return -1;
    }

    public InvoicesDTO getOrderByReservation(int id)
    {
        try {

            Mono<InvoicesDTO> response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/reservations/{id}")
                                    .build(id)
                    )

                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<InvoicesDTO>() {});
            return response.block();

        }catch (Exception e)
        {
            System.out.println("Error in add permission GW");
        }
        return null;
    }

    public int updateOrder(Invoices_detailsDTO invoicesDetailsDTO)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.put()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/updateOrderDetail")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(invoicesDetailsDTO) )
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
