package com.example.covidapplication;

import java.io.Serializable;

public class Relation implements Serializable {
    public final String relation, url, label;
    public final boolean forward;

    public Relation(String relation, String url, String label, boolean forward) {
        this.relation = relation;
        this.url = url;
        this.label = label;
        this.forward = forward;
    }
}
