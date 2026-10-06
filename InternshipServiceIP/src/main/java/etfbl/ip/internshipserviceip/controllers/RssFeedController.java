package etfbl.ip.internshipserviceip.controllers;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;
import etfbl.ip.internshipserviceip.entities.Internship;
import etfbl.ip.internshipserviceip.services.InternshipService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rss")
public class RssFeedController {

    private final InternshipService internshipService;

    public RssFeedController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @GetMapping(produces = MediaType.APPLICATION_RSS_XML_VALUE)
    public Channel rssFeed() {

        List<Internship> internshipList = internshipService.getAllInternships();
        List<Item> items = new ArrayList<>();
        Channel channel = new Channel("rss_2.0");
        channel.setTitle("Internships Feed");
        channel.setLink("http://localhost:8080/internships");
        channel.setDescription("Available internships in the app");

        for(Internship internship : internshipList) {
            Item item = new Item();
            item.setTitle(internship.getName());
            channel.setLink("http://localhost:8080/internships/" + internship.getId());
            Description desc = new Description();
            desc.setType("text/plain");
            desc.setValue(internship.getDescription());
            item.setDescription(desc);

            items.add(item);
        }
        channel.setItems(items);

        return channel;
    }

}
