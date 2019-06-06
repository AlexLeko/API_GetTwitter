package br.com.invillia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopProfileDTO {

	private String profileName;
	
	private Integer followerCount;

//	public TopProfileDTO(String profileName, Integer followerCount) {
//		this.profileName = profileName;
//		this.followerCount = followerCount;
//	}
	
	
	
}
