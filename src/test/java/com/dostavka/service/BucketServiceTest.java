package com.dostavka.service;

import com.dostavka.domain.Bucket;
import com.dostavka.domain.User;
import com.dostavka.repository.BucketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BucketServiceTest {

    @Mock
    private BucketRepository bucketRepository;

    private BucketService bucketService;

    private Bucket bucket;

    private List<Bucket> buckets;

    @BeforeEach
    void setUser() {
        MockitoAnnotations.openMocks(this);
        bucketService = new BucketService(bucketRepository);
        bucket = new Bucket(1, new User(), new ArrayList<>());
        buckets = new ArrayList<>();
        buckets.add(bucket);
        bucketRepository.save(bucket);
    }

    @Test
    void getAllBucket() {
        when(bucketRepository.findAll()).thenReturn(buckets);
        Optional<List<Bucket>> optional = Optional.ofNullable(bucketService.getAllBuckets());
        assertTrue(optional.isPresent());
        verify(bucketRepository).findAll();
    }

    @Test
    void createBucket() {
        Bucket newBucket = new Bucket(7, new User(), new ArrayList<>());
        bucketService.createBucket(newBucket);
        verify(bucketRepository, times(2)).save(ArgumentMatchers.any(Bucket.class));
    }
}