package be.iccbxl.pid.reservationsspringboot.api.controller;


import be.iccbxl.pid.reservationsspringboot.dto.RepresentationDTO;
import be.iccbxl.pid.reservationsspringboot.mapper.RepresentationMapper;
import be.iccbxl.pid.reservationsspringboot.service.RepresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/representations")
public class RepresentationApiController {
    @Autowired
    private RepresentationService service;
    @Autowired
    private RepresentationMapper mapper;

    @GetMapping
    public List<RepresentationDTO> getAll(){
        return mapper.toDTOList(service.getAll());

    }
    @GetMapping ("/{id}")
    public RepresentationDTO getById(@PathVariable String id) {
        return mapper.toDTO( service.get( id ) );
    }

}
