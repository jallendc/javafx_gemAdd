package com.jallen.beyond.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Gem {

    private final StringProperty gemName;
    private final IntegerProperty gemValue;
    private final StringProperty description;

    public Gem() {
        this(null, 0);
    }

    /**
     * Constructor with some initial data.
     *
     * @param gemName;
     * @param gemValue;
     */

    public Gem(String gemName, int gemValue) {
        this.gemName = new SimpleStringProperty(gemName);
        this.gemValue = new SimpleIntegerProperty(gemValue);

        // Some initial dummy data, just for convenient testing.
        //this.description = new SimpleStringProperty("some gem description");
        this.description = new SimpleStringProperty(null);
    }

    public String getGemName() {
        return gemName.get();
    }

    public void setGemName(String gemName) {
        this.gemName.set(gemName);
    }

    public StringProperty gemNameProperty() {
        return gemName;
    }

    public int getGemValue() {
        return gemValue.get();
    }

    public void setGemValue(int gemValue) {
        this.gemValue.set(gemValue);
    }

    public IntegerProperty gemValueProperty() {
        return gemValue;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
