package org.khatri.sto.tags.controller;

import jakarta.validation.Valid;
import org.khatri.sto.tags.dto.request.TagSearchRequest;
import org.khatri.sto.tags.entity.TagMapping;
import org.khatri.sto.tags.service.TagMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankit Khatri
 */

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired private TagMappingService tagMappingService;

    private void throwValidationErrorIfAny(final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = "Validation failed in fields: ".concat(bindingResult.getFieldErrors().stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.joining(",")));
            throw new RuntimeException(errorMessage);
        }
    }

    @PostMapping("/mapping/{refId}")
    public ResponseEntity<?> createQuestionWithTags(@PathVariable Long refId,
                                                    @RequestParam("refType") TagMapping.RefType refType,
                                                    @RequestBody List<String> tagNames) {
        return ResponseEntity.ok(this.tagMappingService.mapTagsWithRefId(refId, refType, tagNames));
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchByTag(@Valid @RequestBody TagSearchRequest request, BindingResult result){
        this.throwValidationErrorIfAny(result);
        return ResponseEntity.ok(this.tagMappingService.searchByTags(request));
    }

    @GetMapping("/find/{refId}")
    public ResponseEntity<?> findTagWithRefId(@PathVariable Long refId,
                                              @RequestParam("refType") TagMapping.RefType refType){
        return ResponseEntity.ok(this.tagMappingService.findTagWithRefId(refId, refType));
    }

}
