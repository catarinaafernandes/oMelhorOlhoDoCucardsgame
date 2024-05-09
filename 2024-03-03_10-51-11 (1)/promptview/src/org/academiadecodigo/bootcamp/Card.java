package org.academiadecodigo.bootcamp;

public class Card {

    private String label;
    private int value;

    public Card(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }
    public int getValue() {
        return value;
    }
}
