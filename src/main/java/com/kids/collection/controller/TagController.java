package com.kids.collection.controller;

import com.kids.collection.request.TagRequest;
import com.kids.collection.response.TagResponse;
import com.kids.collection.services.TagService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {
    private TagService service;

    @GetMapping
    public ResponseEntity<Set<TagResponse>> findTags(@RequestParam(name = "tagName", required = false, defaultValue = "")String tagName){
        Set<TagResponse> response =  service.findTags(tagName.isBlank() ? null : tagName);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TagResponse> createTag(@Valid @RequestBody TagRequest tagRequest){
        TagResponse response = service.createTag(tagRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
