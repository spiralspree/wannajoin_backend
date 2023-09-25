package com.spiralspree.wannajoin.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="test_table")
@Getter
@Setter
public class HbrntTestClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "text")
    private String text;

    public HbrntTestClass () {}

}
