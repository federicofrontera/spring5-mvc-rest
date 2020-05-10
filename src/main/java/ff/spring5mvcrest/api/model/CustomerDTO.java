package ff.spring5mvcrest.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    @ApiModelProperty(value = "Firstname of the customer", required = true)
    private String firstname;
    @ApiModelProperty(value = "Lastname of the customer", required = true)
    private String lastname;
    private String customerURL;
}
