package be.iccbxl.pid.reservationsspringboot.api.controller;


import be.iccbxl.pid.reservationsspringboot.dto.TagDTO;
import be.iccbxl.pid.reservationsspringboot.mapper.TagMapper;
import be.iccbxl.pid.reservationsspringboot.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "*")

public class TagApiController {


    @Autowired
    private TagService tagService;
    @Autowired
    private TagMapper tagMapper;



    @GetMapping
    public List<TagDTO> getAllTags() {
        return tagMapper.toDTOList( tagService.findAll() );
    }



}