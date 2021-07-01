package com.goudourasv.http.kostas;

import java.io.Serializable;

public class AbcExample {
    private String title;

    public AbcExample(String title){
        this.title = title;
    }

    public AbcExample(){
        this.title = "maria";

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
