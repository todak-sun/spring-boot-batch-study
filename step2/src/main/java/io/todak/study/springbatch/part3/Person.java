package io.todak.study.springbatch.part3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Table(name = "PERSON")
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Nationalized
    @Column(name = "NAME")
    private String name;

    @Nationalized
    @Column(name = "AGE")
    private String age;

    @Nationalized
    @Column(name = "ADDRESS")
    private String address;

    public Person(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Person(Long id, String name, String age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
