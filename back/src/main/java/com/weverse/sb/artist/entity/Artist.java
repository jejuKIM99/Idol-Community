package com.weverse.sb.artist.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ARTIST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {
    @Id
    @Column(name = "artist_id")
    private Long artistId;

    @Column(name = "name")
    private String name;

    @Column(name = "stage_name")
    private String stageName;

    @Column(name = "agency_name")
    private String agencyName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "sns_url")
    private String snsUrl;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "status_message")
    private String statusMessage;

    @Column(name = "dm_nickname")
    private String dmNickname;
}
