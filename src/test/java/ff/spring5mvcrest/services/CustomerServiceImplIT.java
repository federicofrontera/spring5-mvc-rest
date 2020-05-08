package ff.spring5mvcrest.services;

import ff.spring5mvcrest.api.mapper.CustomerMapper;
import ff.spring5mvcrest.api.model.CustomerDTO;
import ff.spring5mvcrest.bootstrap.Bootstrap;
import ff.spring5mvcrest.domain.Customer;
import ff.spring5mvcrest.repositories.CategoryRepository;
import ff.spring5mvcrest.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @BeforeEach
    public void setUp() throws Exception{
        System.out.println("Loading Customer data..." + customerRepository.count());

        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void patchCustomerFirstname() throws Exception{
        String newName = "new name";
        long id = customerRepository.findAll().get(0).getId();


        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(newName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(newName, updatedCustomer.getFirstname());
        assertThat(originalFirstname, not(equalTo(updatedCustomer.getFirstname())));
        assertThat(originalLastname, equalTo(updatedCustomer.getLastname()));
    }

    @Test
    public void patchCustomerLastname() throws Exception{
        String newName = "new name";
        long id = customerRepository.findAll().get(0).getId();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(newName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(newName, updatedCustomer.getLastname());
        assertThat(originalLastname, not(equalTo(updatedCustomer.getLastname())));
        assertThat(originalFirstname, equalTo(updatedCustomer.getFirstname()));
    }
}
