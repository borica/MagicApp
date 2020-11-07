package com.example.brcomboricagrid.models;

public class Card {

    private int id;
    private String name;
    private String type;
    private int qtd_mana;

    public Card() {}

    public Card(int id, String name, int qtd_mana, String type){
        this.id = id;
        this.name = name;
        this.qtd_mana = qtd_mana;
        this.type = type;
    }

    public Card(String name, int qtd_mana, String type){
        this.name = name;
        this.qtd_mana = qtd_mana;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQtd_mana() {
        return qtd_mana;
    }

    public void setQtd_mana(int qtd_mana) {
        this.qtd_mana = qtd_mana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
