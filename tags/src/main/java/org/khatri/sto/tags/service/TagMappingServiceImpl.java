package org.khatri.sto.tags.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.khatri.sto.tags.TagMappingConverter;
import org.khatri.sto.tags.dto.TagMappingDto;
import org.khatri.sto.tags.dto.request.TagSearchRequest;
import org.khatri.sto.tags.entity.Tag;
import org.khatri.sto.tags.entity.TagMapping;
import org.khatri.sto.tags.repository.TagMappingRepository;
import org.khatri.sto.tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankit Khatri
 */
@Service
@Slf4j
public class TagMappingServiceImpl implements TagMappingService{

    @Autowired private TagRepository tagRepository;
    @Autowired private TagMappingRepository tagMappingRepository;

    @Override
    public TagMappingDto mapTagsWithRefId(final Long refId, final TagMapping.RefType refType, final List<String> tagNames){
        log.info("Request received for map tags for refId:{}, refType:{}, data:{}", refId, refType, tagNames);
        if(CollectionUtils.isNotEmpty(tagNames)) {
            List<String> normalisedTags = this.normalisedTags(tagNames);
            List<Tag> tags = normalisedTags.stream().map(this::getOrCreateTag).collect(Collectors.toList());
            List<String> tagIds = tags.stream().map(Tag::getId).map(id -> id.toHexString()).collect(Collectors.toList());
            TagMapping existingMapping = tagMappingRepository.findByRefIdAndRefType(refId, refType);
            if(existingMapping != null){
                existingMapping.setTags(tagIds);
            } else{
                existingMapping = TagMappingConverter.convertToEntity(refId, refType, tagIds);
            }
            existingMapping = tagMappingRepository.save(existingMapping);
            log.info("Successfully mapped the tags with the given refId:{}, refType:{}", refId, refType);
            return TagMappingConverter.convertEntityToDto(existingMapping, tags);
        }
        return null;
    }

    private List<String> normalisedTags(List<String> tagNames) {
        return tagNames.stream().filter(StringUtils::isNotEmpty).map(tag -> tag.replaceAll("[^a-zA-Z0-9]", "").trim()).collect(Collectors.toList());
    }

    private Tag getOrCreateTag(final String tagName) {
        Tag tag = this.tagRepository.findByName(tagName);
        if (tag == null) {
            tag = new Tag();
            tag.setName(tagName);
            tag = this.tagRepository.save(tag);
        }
        return tag;
    }

    @Override
    public List<TagMappingDto> searchByTags(final TagSearchRequest request){
        List<String> normalisedTags = this.normalisedTags(request.getTags());
        List<Tag> tags = this.tagRepository.findByNameIn(normalisedTags);
        if(CollectionUtils.isNotEmpty(tags)) {
            List<String> tagIds = tags.parallelStream().map(tag -> tag.getId().toHexString()).toList();
            List<TagMapping> tagMappings = this.tagMappingRepository.findByRefTypeAndTagsIn(request.getRefType(), tagIds);
            if(CollectionUtils.isNotEmpty(tagMappings)){
                return tagMappings.stream().map(tagMapping -> {
                    List<Tag> allTags = tags.parallelStream().filter(tag -> tagMapping.getTags().contains(tag.getId())).toList();
                    return TagMappingConverter.convertEntityToDto(tagMapping, allTags);
                }).toList();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public TagMappingDto findTagWithRefId(final Long refId, final TagMapping.RefType refType){
        TagMapping tagMapping = this.tagMappingRepository.findByRefIdAndRefType(refId, refType);
        if(tagMapping != null){
            List<ObjectId> ids = tagMapping.getTags().stream().map(id -> new ObjectId(id)).toList();
            List<Tag> tags = this.tagRepository.findAllById(ids);
            return TagMappingConverter.convertEntityToDto(tagMapping, tags);
        }
        return null;
    }
}
