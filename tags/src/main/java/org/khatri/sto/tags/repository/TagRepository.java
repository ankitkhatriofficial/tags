package org.khatri.sto.tags.repository;

import org.bson.types.ObjectId;
import org.khatri.sto.tags.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Ankit Khatri
 */
public interface TagRepository extends MongoRepository<Tag, ObjectId> {

    Tag findByName(final String name);
    List<Tag> findByNameIn(final List<String> names);
}
