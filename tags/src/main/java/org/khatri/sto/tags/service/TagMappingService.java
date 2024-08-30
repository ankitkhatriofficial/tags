package org.khatri.sto.tags.service;

import org.khatri.sto.tags.dto.TagMappingDto;
import org.khatri.sto.tags.dto.request.TagSearchRequest;
import org.khatri.sto.tags.entity.TagMapping;

import java.util.List;

/**
 * @author Ankit Khatri
 */
public interface TagMappingService {

    TagMappingDto mapTagsWithRefId(final Long refId, final TagMapping.RefType refType, final List<String> tagNames);

    List<TagMappingDto> searchByTags(final TagSearchRequest request);

    TagMappingDto findTagWithRefId(final Long refId, final TagMapping.RefType refType);
}
