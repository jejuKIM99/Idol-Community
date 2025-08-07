package com.weverse.sb.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupDTO {
    private Long groupId;
    private String groupName;
    private String groupProfileImage;
    private String groupLogo;
}