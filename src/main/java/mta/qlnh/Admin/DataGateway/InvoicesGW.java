package mta.qlnh.Admin.DataGateway;

import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.DO.Invoices;
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

public class InvoicesGW {
    private final WebClient webClient;
    public InvoicesGW()
    {
        String URI = "http://localhost:8080/sub/api/admin/invoices/";
        webClient = WebClient.builder()
                .baseUrl(URI)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public List<Invoices> getAll()
    {
        try {
            Mono<List<Invoices>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/")
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Invoices>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Invoices getById(int id)
    {
        try {
            Mono<Invoices> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Invoices>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public List<Invoices> getByCustormersId(int id)
    {
        try {
            Mono<List<Invoices>> response = webClient.get()
                    .uri(                            uriBuilder -> uriBuilder
                            .path("/getInvoicesByCustormersId/{id}")
                            .build(id)
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Invoices>>() {});
            return response.block();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public int addInvoices(Invoices invoices)
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/add")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(invoices) )
                    .retrieve();
            return_status = Optional.of(response.toBodilessEntity().block().getStatusCode()).get();
            return return_status.value();

        }catch (Exception e)
        {
            System.out.println("Error in add permission group GW");
        }
        return -1;
    }

    public  int deleteInvoices(int id)
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
            System.out.println("Error in delete invoice GW");
        }
        return -1;
    }

    public  int updateInvoices(Invoices invoices )
    {
        try {
            HttpStatusCode return_status;
            WebClient.ResponseSpec response = webClient.put()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path("/update")
                                    .build()
                    )
                    .body(BodyInserters.fromValue(invoices) )
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
