package org.khatri.sto.tags.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khatri.sto.tags.entity.TagMapping;

import java.io.Serializable;

/**
 * @author Ankit Khatri
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TagSearchResponse implements Serializable {

    private static final long serialVersionUID = 43784938984439L;

    private String tag;
    private String refId;
    private TagMapping.RefType refType;
}
