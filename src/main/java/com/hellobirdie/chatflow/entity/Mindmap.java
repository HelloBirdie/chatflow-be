package com.hellobirdie.chatflow.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mindmap")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mindmap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long ownerId;

}
