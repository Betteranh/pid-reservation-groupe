package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.Video;
import be.iccbxl.pid.reservationsspringboot.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("/artist/{name}")
    public String videosByArtist(@PathVariable("name") String artistName, Model model) {
        List<Video> videos = videoService.findVideosByArtist(artistName);
        model.addAttribute("artistName", artistName);
        model.addAttribute("videos", videos);
        return "video/artist-video";
    }
}
