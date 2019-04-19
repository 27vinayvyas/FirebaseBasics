package com.example.vinay.fireebase;

public class Item {

    String id;
    String name;
    String comment;
    String sequence;

    public Item(){

    }

    public Item(String id, String name, String comment,String sequence) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.sequence=sequence;
    }

    public String getId() {
        return id;
    }

    public  String getSequence(){
        return  sequence;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }
}
