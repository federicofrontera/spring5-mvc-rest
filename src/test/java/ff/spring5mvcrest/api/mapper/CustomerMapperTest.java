package ff.spring5mvcrest.api.mapper;

import ff.spring5mvcrest.api.model.CategoryDTO;
import ff.spring5mvcrest.api.model.CustomerDTO;
import ff.spring5mvcrest.domain.Category;
import ff.spring5mvcrest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerMapperTest {
    private final String TEST_FIRSTNAME = "John";
    private final String TEST_LASTNAME = "Smith";
    private final long ID = 1l;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void CategoryToCategoryDTOTest() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setId(Long.valueOf(ID));
        customer.setFirstname(TEST_FIRSTNAME);
        customer.setLastname(TEST_LASTNAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(TEST_FIRSTNAME, customerDTO.getFirstname());
        assertEquals(TEST_LASTNAME, customerDTO.getLastname());
    }
}
