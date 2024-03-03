package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Invoice_details;
import mta.qlnh.Admin.DTO.InvoicesDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class CashGW {
    private final WebClient webClient;

    public CashGW() {
        String URI = "http://localhost:8080/sub/api/admin/cash/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public List<InvoicesDTO> getBillWithFilter(String startD, String endD, String startH, String endH, String customerName, String customerNum) {
        try {
            Mono<List<InvoicesDTO>> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/")
                            .queryParam("name", startD)
                            .queryParam("description", endD)
                            .queryParam("name", startH)
                            .queryParam("description", endH)
                            .queryParam("name", customerName)
                            .queryParam("description", customerNum)
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<InvoicesDTO>>() {
                    });
            return response.block();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public InvoicesDTO get1Bill(int id) {
        try {
            Mono<InvoicesDTO> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<InvoicesDTO>() {
                    });
            return response.block();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public InvoicesDTO getBillOfCurrenReservation(int id) {
        try {
            Mono<InvoicesDTO> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/GetBill/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<InvoicesDTO>() {
                    });
            return response.block();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public InvoicesDTO ClearCurrenReservation(int id) {
        try {
            Mono<InvoicesDTO> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/ClearReservation/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<InvoicesDTO>() {
                    });
            return response.block();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public InvoicesDTO PaymentConfirm(int id) {
        try {
            Mono<InvoicesDTO> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/paid/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<InvoicesDTO>() {
                    });
            return response.block();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
