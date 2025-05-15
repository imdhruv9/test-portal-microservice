package com.microservice.exam_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="questions",uniqueConstraints = {
        @UniqueConstraint(columnNames = "Question-text")
})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String questionText;  //

    @ManyToOne
    @JoinColumn(name = "topic_id",nullable = false)
    private Topic topic;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options;

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

