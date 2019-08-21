/**
 * 
 */
package com.citi.quest.api.util;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.citi.quest.api.domain.Sequence;

/**
 * Service class responsible for generating unique sequence, in a thread safe manner,
 * for insert operations.
 * 
 * @author Ashutosh Srivastav
 * @createdOn Aug 21, 2019
 */
@Service
public class SequenceGenerator {

	/** Logger for class. */
	private Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	/** Name of _id field in the collection. */
	private static final String FIELD_ID = "sequenceName";

	/** Name of field representing sequence counter. */
	private static final String FIELD_NEXT_SEQ = "nextSequence";

	@Autowired
	private MongoOperations mongoOperations;

	/**
	 * Returns the next available sequence to be assigned for insert operations.
	 * This operation is thread safe and guarantees that no duplicate sequences
	 * are generated.
	 * 
	 * @param domainSequenceKey - a key uniquely representing a domain object
	 * @return the next available sequence as <Long>
	 */
	public Long getNextSequence(String domainSequenceKey) {
		LOGGER.info("Entry");

		Criteria criteria = Criteria.where(FIELD_ID).is(domainSequenceKey);
		Query query = new Query(criteria);

		Update update = new Update();
		update.inc(FIELD_NEXT_SEQ, 1);

		FindAndModifyOptions options = FindAndModifyOptions.options()
				.returnNew(true) //return the updated doc
				.upsert(true); //create for the first time

		// update the counter in a thread safe way
		Sequence sequence = mongoOperations.findAndModify(query, update, options, Sequence.class);
		Long nextSequence = sequence.getNextSequence();

		LOGGER.info("Returning next sequence: {} for key: {}", nextSequence, domainSequenceKey);
		return nextSequence;
	}

}
