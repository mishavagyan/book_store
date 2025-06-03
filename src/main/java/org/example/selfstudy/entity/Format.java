package org.example.selfstudy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Format {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Format)) return false;
        Format format = (Format) o;
        return name.equalsIgnoreCase(format.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

}
