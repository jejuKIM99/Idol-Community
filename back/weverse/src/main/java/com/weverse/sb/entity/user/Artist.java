package com.weverse.sb.entity.user;

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
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private ArtistGroupMap group;

    @Column(name = "stage_name", length = 100, nullable = false)
    private String stageName;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
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
