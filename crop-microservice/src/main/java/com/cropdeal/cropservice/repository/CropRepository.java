package com.cropdeal.cropservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cropdeal.cropservice.models.Crop;


public interface CropRepository extends MongoRepository<Crop, String> {

	List<Crop> findByFarmerId(String farmerId);

} 