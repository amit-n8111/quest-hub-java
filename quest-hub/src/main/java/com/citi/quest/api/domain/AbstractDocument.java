/**
 * 
 */
package com.citi.quest.api.domain;

import org.springframework.data.annotation.Id;

/**
 * @author Ashutosh.Srivastav
 *
 */
public class AbstractDocument {
	
	@Id
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}
		
		AbstractDocument that = (AbstractDocument) obj;
		
		return this.id.equals(that.getId());
	}
	
	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

}
