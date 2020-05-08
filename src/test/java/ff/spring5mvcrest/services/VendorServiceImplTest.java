package ff.spring5mvcrest.services;

import ff.spring5mvcrest.api.mapper.VendorMapper;
import ff.spring5mvcrest.api.model.CustomerDTO;
import ff.spring5mvcrest.api.model.VendorDTO;
import ff.spring5mvcrest.controllers.v1.CustomerController;
import ff.spring5mvcrest.controllers.v1.VendorController;
import ff.spring5mvcrest.domain.Customer;
import ff.spring5mvcrest.domain.Vendor;
import ff.spring5mvcrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {
    private final String TEST_NAME_1 = "test vendor 1";
    private final String TEST_NAME_2 = "test vendor 2";
    private final long ID = 1l;
    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;
    VendorService vendorService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorMapper, vendorRepository);
    }

    @Test
    public void getAllVendors() throws Exception {
        //given
        Vendor vendor1 = new Vendor();
        vendor1.setName(TEST_NAME_1);
        Vendor vendor2 = new Vendor();
        vendor2.setName(TEST_NAME_2);
        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1, vendor2));

        //when
        List<VendorDTO> vendorDTOList = vendorService.getAllVendors();

        //then
        assertEquals(2, vendorDTOList.size());
    }

    @Test
    public void getVendorById() throws Exception {
        //given
        Vendor vendor = new Vendor();
        vendor.setName(TEST_NAME_1);
        vendor.setId(ID);
        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(vendor));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        //then
        assertEquals(TEST_NAME_1, vendorDTO.getName());

    }

    @Test
    public void createNewVendor() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(TEST_NAME_1);
        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1l);
        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDto.getVendorURL());
    }

    @Test
    public void saveVendorByDTO() throws Exception{
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(TEST_NAME_1);

        Vendor savedVendor = new Vendor();
        vendorDTO.setName(TEST_NAME_1);
        savedVendor.setId(ID);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDTO = vendorService.saveVendorByDTO(ID, vendorDTO);

        //then
        assertEquals(savedVendor.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/" + ID, savedDTO.getVendorURL());
    }

}
