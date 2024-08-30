package org.khatri.sto.tags.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.khatri.sto.tags.entity.TagMapping;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ankit Khatri
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TagMappingDto implements Serializable {

    private static final long serialVersionUID = 238794984839438L;

    private Long refId;
    private TagMapping.RefType refType;
    private List<String> tags;
}
