package ff.spring5mvcrest.services;

import ff.spring5mvcrest.api.model.CustomerDTO;
import ff.spring5mvcrest.api.model.VendorDTO;
import ff.spring5mvcrest.api.model.VendorListDTO;

import java.util.List;

public interface VendorService {
    VendorListDTO getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);
    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
    void deleteVendorById(Long id);
}
