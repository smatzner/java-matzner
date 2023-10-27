package com.example.cinema.entities;

public enum MovieVersion {
    D2D("Digital 2D"),
    R3D("Real D 3D"),
    DBOX("D-BOX 5D");

    public final String label;

    MovieVersion(String label) {
        this.label = label;
    }
}
