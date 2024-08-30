package org.khatri.sto.tags.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ankit Khatri
 */

@Data
@Document(collection = "tags")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Serializable {

    private static final long serialVersionUID = 3234789348943L;

    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Indexed(unique = true)
    private String name;

    @CreatedDate
    private Date createdAt;
}
