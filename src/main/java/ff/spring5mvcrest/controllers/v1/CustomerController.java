package ff.spring5mvcrest.controllers.v1;

import ff.spring5mvcrest.api.model.CategoryDTO;
import ff.spring5mvcrest.api.model.CustomerDTO;
import ff.spring5mvcrest.api.model.CustomerListDTO;
import ff.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomers(){
        return new ResponseEntity<CustomerListDTO>
                (new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCategoryByName(@PathVariable String id){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(Long.valueOf(id)),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }
}
