package com.uniquehire.rolemanagement.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "organizations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "org_name", nullable = false)
    private String orgName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String code;
}