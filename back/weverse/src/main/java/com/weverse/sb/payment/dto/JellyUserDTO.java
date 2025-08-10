package com.weverse.sb.payment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JellyUserDTO {
	private String email;
	private String name;
	private int chargedJelly;
	private int bonusJelly;
	private int totalJelly;
}
