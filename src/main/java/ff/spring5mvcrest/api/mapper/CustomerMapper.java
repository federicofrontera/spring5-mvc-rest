package ff.spring5mvcrest.api.mapper;

import ff.spring5mvcrest.api.model.CustomerDTO;
import ff.spring5mvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
