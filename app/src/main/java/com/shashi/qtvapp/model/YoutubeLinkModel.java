package com.shashi.qtvapp.model;

public class YoutubeLinkModel implements Comparable<YoutubeLinkModel> {

    private int id;
    private String link;

    public YoutubeLinkModel() {

    }

    public YoutubeLinkModel(String link, int id) {
        this.link = link;
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(YoutubeLinkModel user2) {
        return Integer.compare(user2.getId(), getId());
    }
}