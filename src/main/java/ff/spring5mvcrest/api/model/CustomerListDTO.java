package ff.spring5mvcrest.api.model;

import ff.spring5mvcrest.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDTO {
    List<Customer> customers;
}
