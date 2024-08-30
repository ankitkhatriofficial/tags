package org.khatri.sto.tags;

import org.khatri.sto.tags.dto.TagMappingDto;
import org.khatri.sto.tags.entity.Tag;
import org.khatri.sto.tags.entity.TagMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankit Khatri
 */
public class TagMappingConverter {

    public static TagMappingDto convertEntityToDto(final TagMapping tagMapping, List<Tag> tags){
        return TagMappingDto.builder().refId(tagMapping.getRefId()).refType(tagMapping.getRefType())
                .tags(tags.stream().map(Tag::getName).toList()).build();
    }

    public static TagMapping convertToEntity(final Long refId, final TagMapping.RefType refType, final List<String> tagIds) {
        return TagMapping.builder().refId(refId).refType(refType).tags(tagIds).build();
    }
}
