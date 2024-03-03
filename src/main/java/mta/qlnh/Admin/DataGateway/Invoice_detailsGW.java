package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Invoice_details;
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

public class Invoice_detailsGW {
    private final WebClient webClient;

    public Invoice_detailsGW() {
        String URI = "http://localhost:8080/sub/api/admin/invoice_details/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public List<Invoice_details> getAll() {
        try {
            Mono<List<Invoice_details>> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/")
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Invoice_details>>() {
                    });
            return response.block();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }



public  int addInvoice_details(Invoice_details invoice_details)
{
    try {
        HttpStatusCode return_status;
        WebClient.ResponseSpec response = webClient.post()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/")
                                .build()
                )
                .body(BodyInserters.fromValue(invoice_details) )
                .retrieve();
        return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
        return return_status.value();

    }catch (Exception e)
    {
        System.out.println("Error in add add Invoice_details GW");
    }
    return -1;
}

    public  int deleteInvoice_details(int id)
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
            System.out.println("Error in delete Invoice_details GW");
        }
        return -1;
    }

    public  int updateInvoice_details(Invoice_details invoice_details)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.put()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/update")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(invoice_details) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in update Invoice_details GW");
        }
        return -1;
    }
}
