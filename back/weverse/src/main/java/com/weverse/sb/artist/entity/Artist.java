package com.weverse.sb.artist.entity; 

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Entity
@Table(name = "ARTIST")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long artistId;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "name", length = 100)
    private String name;
    
    @Column(name = "stage_name", length = 100)
    private String stageName;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "profile_image", length = 2083)
    private String profileImage;

    @Column(name = "sns_url", length = 2083)
    private String snsUrl;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Column(name = "status_message", length = 255)
    private String statusMessage;

    @Column(name = "dm_nickname", length = 100)
    private String dmNickname;
}