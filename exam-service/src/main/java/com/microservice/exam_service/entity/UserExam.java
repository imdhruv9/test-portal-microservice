package com.microservice.exam_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @ManyToOne
    private Exam exam;

    @ElementCollection
    private List<Long> questionIds;

    @ElementCollection
    private Map<Long, List<String>> shuffledOptions; // questionId -> options

    @ElementCollection
    private Map<Long, String> userAnswers;

    private LocalDateTime startedAt;
    private LocalDateTime submittedAt;

    @Column(name="correct_ans")
    private Integer correctAns;

    private LocalDate createdDate;

    private boolean isActive;
    @PrePersist
    public void prePersist(){
        this.createdDate = LocalDate.now();
        this.isActive = true;

    }
}

