package be.iccbxl.pid.reservationsspringboot.model;

public class RssItem {
    private String title;
    private String link;
    private String description;
    private String author;
    private String pubDate;
    private String guid;

    public RssItem(String title, String link, String description, String author, String pubDate, String guid) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.author = author;
        this.pubDate = pubDate;
        this.guid = guid;
    }

    // Getters uniquement (pas besoin de setters ici)
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getDescription() { return description; }
    public String getAuthor() { return author; }
    public String getPubDate() { return pubDate; }
    public String getGuid() { return guid; }
}
