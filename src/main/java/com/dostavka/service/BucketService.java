package com.dostavka.service;

import com.dostavka.domain.Bucket;
import com.dostavka.domain.User;
import com.dostavka.repository.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BucketService {
    BucketRepository bucketRepository;

    @Autowired
    public BucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    public Bucket getBucketById(int id){
        return bucketRepository.findById(id).get();
    }

    public ArrayList<Bucket> getAllBuckets() {
        return (ArrayList<Bucket>) bucketRepository.findAll();
    }

    public Bucket createBucket(Bucket bucket) {
        return bucketRepository.save(bucket);
    }

    public Bucket updateBucketById(Bucket bucket) {
        return bucketRepository.saveAndFlush(bucket);
    }

    public void deleteBucket(Bucket bucket) {
        bucketRepository.delete(bucket);
    }
}
