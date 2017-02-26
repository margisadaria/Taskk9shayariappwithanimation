package com.margi.task9_shayarifragment;

/**
 * Created by Margi on 24-Feb-17.
 */
public class Post2
{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    private int id, cat_id;
    private String quote;

}
