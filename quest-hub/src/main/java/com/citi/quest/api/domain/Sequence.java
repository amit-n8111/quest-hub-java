/**
 * 
 */
package com.citi.quest.api.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Document class for "sequence" collection.
 * 
 * @author Ashutosh Srivastav
 * @createdOn Aug 21, 2019
 */
@Document
public class Sequence implements Serializable {

	/** default serial version. */
	private static final long serialVersionUID = 1L;

	@Id
	private String sequenceName;

	private Long nextSequence;

	/**
	 * @return the sequenceName
	 */
	public String getSequenceName() {
		return sequenceName;
	}

	/**
	 * @param sequenceName the sequenceName to set
	 */
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	/**
	 * @return the nextSequence
	 */
	public Long getNextSequence() {
		return nextSequence;
	}

	/**
	 * @param nextSequence the nextSequence to set
	 */
	public void setNextSequence(Long nextSequence) {
		this.nextSequence = nextSequence;
	}

}
