package ff.spring5mvcrest.api.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String customerURL;
}