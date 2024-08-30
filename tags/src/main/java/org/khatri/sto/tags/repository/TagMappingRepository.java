package org.khatri.sto.tags.repository;

/**
 * @author Ankit Khatri
 */
import org.bson.types.ObjectId;
import org.khatri.sto.tags.entity.TagMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TagMappingRepository extends MongoRepository<TagMapping, ObjectId> {

//    @Query("{ 'refType': ?1, 'tagIds': { $in: ?2 } }")
    List<TagMapping> findByRefTypeAndTagsIn(final TagMapping.RefType refType, final List<String> tagIds);

    @Query("{ 'refId': ?0, 'refType': ?1 }")
    TagMapping findByRefIdAndRefType(final Long refId, final TagMapping.RefType refType);
}
