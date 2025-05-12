package com.microservice.user_service.entity;

import com.microservice.user_service.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Builder.Default
    private String createdBy = "SELF"; // for simplicity ,  later we will do automapping

    @Column(nullable = false)
    private LocalDate createdDate;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private LocalDate lastModifiedDate;


    @PrePersist
    public void prePersist(){
        this.createdDate = LocalDate.now();
        this.isActive = true;
        this.lastModifiedDate = LocalDate.now();

    }
    @PreUpdate
    public void preUpdate(){
        this.lastModifiedDate = LocalDate.now();
    }


}

