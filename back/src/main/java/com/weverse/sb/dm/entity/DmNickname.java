package com.weverse.sb.dm.entity;

import com.weverse.sb.artist.entity.Artist;
import com.weverse.sb.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DM_NICKNAME")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DmNickname {
    @Id
    @Column(name = "dm_nickname_id")
    private Long dmNicknameId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "nickname")
    private String nickname;
}