package ff.spring5mvcrest.api.mapper;

import ff.spring5mvcrest.api.model.CategoryDTO;
import ff.spring5mvcrest.api.model.VendorDTO;
import ff.spring5mvcrest.domain.Category;
import ff.spring5mvcrest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorMapperTest {
    private final String TEST_NAME = "test vendor";
    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTOTest(){
        //given
        Vendor vendor = new Vendor();
        vendor.setName(TEST_NAME);

        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        //then
        assertEquals(TEST_NAME, vendorDTO.getName());
    }
}
