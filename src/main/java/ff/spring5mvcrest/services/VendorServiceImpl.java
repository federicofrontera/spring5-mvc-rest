package ff.spring5mvcrest.services;

import ff.spring5mvcrest.api.mapper.VendorMapper;
import ff.spring5mvcrest.api.model.CustomerDTO;
import ff.spring5mvcrest.api.model.VendorDTO;
import ff.spring5mvcrest.api.model.VendorListDTO;
import ff.spring5mvcrest.controllers.v1.CustomerController;
import ff.spring5mvcrest.controllers.v1.VendorController;
import ff.spring5mvcrest.domain.Customer;
import ff.spring5mvcrest.domain.Vendor;
import ff.spring5mvcrest.exceptions.ResourceNotFoundException;
import ff.spring5mvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorURL(getVendorURL(vendor.getId()));
                    return vendorDTO;
                }).collect(Collectors.toList()));
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorURL(getVendorURL(vendor.getId()));
                    return vendorDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor savedVendor = new Vendor();
        savedVendor.setId(id);
        return saveAndReturnDTO(savedVendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }
                    VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
                    returnDTO.setVendorURL(getVendorURL(id));
                    return returnDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private String getVendorURL(Long id){
        return VendorController.BASE_URL + "/"  + id;
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        returnDTO.setVendorURL(getVendorURL(savedVendor.getId()));

        return returnDTO;
    }
}
