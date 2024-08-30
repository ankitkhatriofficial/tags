package org.khatri.sto.tags.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Ankit Khatri
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tag_mappings")
public class TagMapping implements Serializable {

    private static final long serialVersionUID = 34784898348943L;

    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Indexed
    private Long refId;

    @Indexed
    private RefType refType;

    private List<String> tags;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    @Getter
    public enum RefType{
        QUESTION, ANSWER
    }
}
