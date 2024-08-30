package org.khatri.sto.tags.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagSearchRequest implements Serializable {

    private static final long serialVersionUID = 32782379933232L;

    @NotNull(message = "tags can't be empty")
    private List<String> tags;

//    @NotNull(message = "refId can't be empty")
    private Long refId;

    @NotNull(message = "refType can't be empty")
    private TagMapping.RefType refType;
}
