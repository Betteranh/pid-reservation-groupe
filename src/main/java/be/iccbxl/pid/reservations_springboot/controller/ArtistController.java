package be.iccbxl.pid.reservations_springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import be.iccbxl.pid.reservations_springboot.model.Artist;
import be.iccbxl.pid.reservations_springboot.service.ArtistService;

@RestController
public class ArtistController {
    @Autowired
    ArtistService service;

    @GetMapping("/artists")
    public String index() {
        StringBuilder content = new StringBuilder("<ul>");

        List<Artist> artists = service.getAllArtists();
        artists.forEach(artist -> {
            content.append("<li>"+artist+"</li>");
        });
        content.append("</ul>");

        return content.toString();
    }

}


