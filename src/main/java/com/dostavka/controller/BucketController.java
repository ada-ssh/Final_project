package com.dostavka.controller;

import com.dostavka.domain.Bucket;
import com.dostavka.service.BucketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/buckets", produces = MediaType.APPLICATION_JSON_VALUE)
public class BucketController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Bucket>> getAllBuckets() {
        return new ResponseEntity<>(bucketService.getAllBuckets() != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Bucket> getBucketById(@PathVariable int id) {
        Bucket bucket = bucketService.getBucketById(id);
        return new ResponseEntity<>(bucket, bucket.getId() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createBucket(@RequestBody @Valid Bucket bucket, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(bucketService.createBucket(bucket) != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateBucketById(@RequestBody @Valid Bucket bucket, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(bucketService.updateBucketById(bucket) != null ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);

    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody Bucket bucket) {
        bucketService.deleteBucket(bucket);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
