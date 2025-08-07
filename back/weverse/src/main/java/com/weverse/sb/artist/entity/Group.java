package com.weverse.sb.artist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`GROUP`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id") 
    private long id;
    
    @Column(name = "group_name")
    private String name;
    
    @Column(name = "group_profile_image")
    private String groupImage;
    
    @Column(name = "group_logo")
    private String logo;
}