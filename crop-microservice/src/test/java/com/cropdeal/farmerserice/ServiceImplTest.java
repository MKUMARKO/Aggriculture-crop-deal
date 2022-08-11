package com.cropdeal.farmerserice;

import com.cropdeal.cropservice.Exception.CropNotFoundException;
import com.cropdeal.cropservice.models.Crop;
import com.cropdeal.cropservice.repository.CropRepository;
import com.cropdeal.cropservice.serviceImpl.CropServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ServiceImplTest.class)
@RunWith(SpringRunner.class)
class ServiceImplTest {

    @Mock
    Crop crop;

    @Mock
    CropRepository cropRepository;


    @InjectMocks
    CropServiceImpl cropServiceImpl;

    @Test
    void addItemTest(){
        cropServiceImpl.addCrop(crop);
        verify(cropRepository,times(1)).save(any());
    }

    @Test
    void updateCropTest(){
        crop = new Crop();
        Mockito.when(cropRepository.findById(any())).thenReturn(Optional.of(crop));
        cropServiceImpl.updateCrop(crop);
        verify(cropRepository,times(1)).save(any());
    }

    @Test
    void viewAllCrops(){
        Mockito.when(cropRepository.findAll()).thenReturn(Stream.of(crop).collect(Collectors.toList()));
        assertEquals(1,cropServiceImpl.viewAllCrop().size());
    }

    @Test
    void viewAllCropsException(){
        Mockito.when(cropRepository.findAll()).thenReturn(null);
        Exception exception = assertThrows(CropNotFoundException.class, () -> {
            cropServiceImpl.viewAllCrop();
        });
        String expectedMessage = "crop not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void viewCropException(){
        Mockito.when(cropRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(CropNotFoundException.class, () -> {
            cropServiceImpl.viewCrop(any());
        });
        String expectedMessage = "crop not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void viewCropTest(){
        Mockito.when(cropRepository.findById(any())).thenReturn(Optional.of(crop));
        cropServiceImpl.viewCrop(any());
        verify(cropRepository,times(1)).findById(any());
    }

    @Test
    void deleteCropTest(){
        Mockito.when(cropRepository.findById(any())).thenReturn(Optional.of(crop));
        String string= cropServiceImpl.deleteCrop(any());
        verify(cropRepository,times(1)).deleteById(any());
        assertEquals("Deleted Successfully", string);

    }

    @Test
    void deleteCropTestNotFound(){
        Mockito.when(cropRepository.findById(any())).thenReturn(Optional.empty());
        String string= cropServiceImpl.deleteCrop(any());
        assertEquals("crop Not found", string);
    }

    @Test
    void viewAllCropByFarmer(){
        Mockito.when(cropRepository.findByFarmerId(any())).thenReturn(Stream.of(crop).collect(Collectors.toList()));
        assertEquals(1,cropServiceImpl.viewAllCropByFarmer(any()).size());
    }

    @Test
    void viewAllCropByFarmerException(){
        Mockito.when(cropRepository.findByFarmerId(any())).thenReturn(null);
        Exception exception = assertThrows(CropNotFoundException.class, () -> {
            cropServiceImpl.viewAllCropByFarmer(any());
        });
        String expectedMessage = "crop not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}