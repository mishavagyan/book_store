package org.example.selfstudy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "awards", schema = "public")
public class Award {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int awardId;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "award")
    @JsonBackReference("award-books")
    private Set<AwardBook> books = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Award)) return false;
        Award award = (Award) o;
        return Objects.equals(name, award.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}



//package org.example.selfstudy.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Objects;
//
//@Getter
//@Setter
//public class Award {
//    private int awardId;
//    private String name;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Award)) return false;
//        Award award = (Award) o;
//        return Objects.equals(name, award.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
//}
