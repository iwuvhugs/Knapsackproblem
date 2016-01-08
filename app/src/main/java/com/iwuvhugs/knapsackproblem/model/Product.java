package com.iwuvhugs.knapsackproblem.model;

public class Product {

    private double id;
    private String title;
    private String body_html;
    private Images[] images;
    private Variants[] variants;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public Images[] getImages() {
        return images;
    }

    public void setImages(Images[] images) {
        this.images = images;
    }

    public Variants[] getVariants() {
        return variants;
    }

    public void setVariants(Variants[] variants) {
        this.variants = variants;
    }


}
