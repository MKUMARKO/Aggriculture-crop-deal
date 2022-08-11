package com.cropdeal.cropservice.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cropdeal.cropservice.Exception.CropNotFoundException;
import com.cropdeal.cropservice.models.Crop;
import com.cropdeal.cropservice.repository.CropRepository;
import com.cropdeal.cropservice.service.CropService;

@Service
public class CropServiceImpl implements CropService {

	public static Logger logFarmer = LoggerFactory.getLogger(CropServiceImpl.class);

	@Autowired
	private CropRepository cropRepo;

	// adding the crop
	@Override
	public Crop addCrop(Crop crop) {
		return cropRepo.save(crop);

	}

	// updating the crop

	@Override
	public Crop updateCrop(Crop crop) throws CropNotFoundException {
		Optional<Crop> crp = cropRepo.findById(crop.getId());

		Crop cropRecord = null;
		if (crp.isPresent()) {
			cropRecord = crp.get();
			cropRepo.save(crop);
		} else {
			logFarmer.error("crop not found");
		}
		return cropRecord;
	}

	// view all the crop

	@Override
	public List<Crop> viewAllCrop() throws CropNotFoundException {

		List<Crop> crop = cropRepo.findAll();
		if (crop == null) {
			throw new CropNotFoundException("crop not found");
		} else

			return crop;
	}

	// view crop by crop id

	@Override
	public Crop viewCrop(String id) throws CropNotFoundException {

		Optional<Crop> crop = cropRepo.findById(id);

		Crop crp = null;
		if (crop.isPresent()) {

			crp = crop.get();
		} else {
			throw new CropNotFoundException("crop not found");
		}
		return crp;
	}

	// delete crop

	@Override
	public String deleteCrop(String id) throws CropNotFoundException {

		String message = null;

		Optional<Crop> crop = cropRepo.findById(id);
		
		System.out.println(crop);
		
		if (crop.isPresent()) {
			cropRepo.deleteById(id);
			message = "Deleted Successfully";
			logFarmer.info(message);
			
			
		} else {
			message = "crop Not found";
			logFarmer.error(message);
		}
		return message;
	}

	// view crop by farmer id

	/*
	 * @Override public Crop viewCropByFarmer(String farmerId) throws
	 * CropNotFoundException {
	 * 
	 * Optional<Crop> crop = cropRepo.findByFarmerId(farmerId);
	 * 
	 * Crop crp = null; if (crop.isPresent()) {
	 * 
	 * crp = crop.get(); } else { throw new CropNotFoundException("crop not found");
	 * } return crp;
	 * 
	 * }
	 */
	@Override
	public List<Crop> viewAllCropByFarmer(String farmerId) throws CropNotFoundException {

		List<Crop> crop = cropRepo.findByFarmerId(farmerId);
		if (crop == null) {
			throw new CropNotFoundException("crop not found");
		} else

			return crop;

	}

}
