package com.example.Job.Offer.Analyst;

public enum Pages {
    PAGE_1("https://www.pracuj.pl/praca/java;kw"),
    PAGE_2("https://www.pracuj.pl/praca/python;kw"),
    PAGE_3("https://www.pracuj.pl/praca/c%2B%2B;kw"),
    PAGE_4("https://www.pracuj.pl/praca/JavaScript;kw"),
    PAGE_5("https://www.pracuj.pl/praca/c%23;kw");

    private final String url;

    Pages(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

}
