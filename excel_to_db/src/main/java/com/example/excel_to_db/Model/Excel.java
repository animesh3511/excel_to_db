package com.example.excel_to_db.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "excel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Excel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "description")
    private String description;

    @Column(name = "website")
    private String website;

    @Column(name = "contact")
    private Double contact;


}
