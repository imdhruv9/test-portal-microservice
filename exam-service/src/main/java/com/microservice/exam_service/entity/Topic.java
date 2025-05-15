package com.microservice.exam_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="topics", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "user_id",nullable = false)
    private Long createdBy;

    private LocalDate createdDate;

    private boolean isActive;
    @PrePersist
    public void prePersist(){
        this.createdDate = LocalDate.now();
        this.isActive = true;

    }
}

