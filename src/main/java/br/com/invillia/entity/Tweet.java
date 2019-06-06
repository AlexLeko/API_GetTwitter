package br.com.invillia.entity;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Tweet implements Serializable{
//implements Comparable<Tweet>
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String texto;
	
	private String profileName;
	
	private Integer followersCount;
	

	public Tweet() { }
	
	public Tweet(String texto) {
		this.texto = texto;
	}
		
	public Tweet(String texto, String profileName, Integer followersCount) {
		this.texto = texto;
		this.profileName = profileName;
		this.followersCount = followersCount;
	}
	
	

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Integer getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followersCount == null) ? 0 : followersCount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((profileName == null) ? 0 : profileName.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (followersCount == null) {
			if (other.followersCount != null)
				return false;
		} else if (!followersCount.equals(other.followersCount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (profileName == null) {
			if (other.profileName != null)
				return false;
		} else if (!profileName.equals(other.profileName))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	
//	@Override
//	public int compareTo(Tweet o) {
//		if(o.getFollowersCount() < this.getFollowersCount())
//			return -1;
//		
//		if(o.getFollowersCount() == this.getFollowersCount())
//			return 0;
//		
//		return 1;
//	}

	

	
	
	

}
