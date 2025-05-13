package be.iccbxl.pid.reservationsspringboot.api.controller;

import be.iccbxl.pid.reservationsspringboot.dto.LocalityDTO;
import be.iccbxl.pid.reservationsspringboot.mapper.LocationShowMapper;
import be.iccbxl.pid.reservationsspringboot.model.Locality;
import be.iccbxl.pid.reservationsspringboot.service.LocalityService;
import be.iccbxl.pid.reservationsspringboot.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/localities")
public class LocalityApiController {


    @Autowired
    private LocalityService localityService;
    @Autowired
    private LocationShowMapper mapper;


    @GetMapping
    public List<LocalityDTO> getAll() {
        return localityService.getAll()
                .stream()
                .map( mapper::mapLocalityToLocalityDTO)
                .collect( Collectors.toList() );
    }


    @GetMapping("/{id}")
    public LocalityDTO get(@PathVariable String id) {
        Locality locality = localityService.get( id );
        return mapper.mapLocalityToLocalityDTO( locality );
    }





}
