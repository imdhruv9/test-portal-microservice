package com.microservice.exam_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topic_id",nullable = false)
    private Topic topic;

    @Column(name = "no_of_question",nullable = false)
    private Integer noOfQuestion;

    @Column(name = "created_by_user_id",nullable = false)
    private Long createdBy;


    private LocalDate createdDate;

    private boolean isActive;

    @PrePersist
    public void prePersist(){
        this.createdDate = LocalDate.now();
        this.isActive = true;

    }

}
