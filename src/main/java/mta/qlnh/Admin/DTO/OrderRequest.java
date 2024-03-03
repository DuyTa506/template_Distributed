package mta.qlnh.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private int invoiceDetail_id;
    private int reservations_id;
    private int invoice_id;
    private int customers_id;
    private List<OrderDTO> orderDTOList;
}
