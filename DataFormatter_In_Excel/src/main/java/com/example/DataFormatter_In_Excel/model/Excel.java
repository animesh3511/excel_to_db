package com.example.DataFormatter_In_Excel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "excel")
public class Excel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "description")
    private String description;

    @Column(name = "website")
    private String website;

    @Column(name = "contact")
    private String contact;

    @Column(name = "date")
    private String date;

    @Column(name = "price")
    private String price;

    @Column(name = "percentage")
    private String percentage;


}
