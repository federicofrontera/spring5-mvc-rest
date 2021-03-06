package ff.spring5mvcrest.services;

import ff.spring5mvcrest.api.mapper.CustomerMapper;
import ff.spring5mvcrest.api.model.CustomerDTO;
import ff.spring5mvcrest.controllers.v1.CustomerController;
import ff.spring5mvcrest.domain.Customer;
import ff.spring5mvcrest.exceptions.ResourceNotFoundException;
import ff.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerURL(getCustomerURL(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerURL(getCustomerURL(customer.getId()));
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setCustomerURL(getCustomerURL(savedCustomer.getId()));

        return returnDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(
                customer -> {
                    if(customerDTO.getFirstname() != null){
                        customer.setFirstname(customerDTO.getFirstname());
                    }
                    if(customerDTO.getLastname() != null){
                        customer.setLastname(customerDTO.getLastname());
                    }

                    CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
                    returnDTO.setCustomerURL(getCustomerURL(id));
                    return returnDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private String getCustomerURL(Long id){
        return CustomerController.BASE_URL + "/"  + id;
    }
}
