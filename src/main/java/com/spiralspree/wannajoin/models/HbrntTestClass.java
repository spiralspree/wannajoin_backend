package com.spiralspree.wannajoin.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="test_table")
@Getter
@Setter
public class HbrntTestClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long Id;

    @Column(name = "text")
    private String text;

}
