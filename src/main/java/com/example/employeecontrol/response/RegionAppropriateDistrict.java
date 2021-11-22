package com.example.employeecontrol.response;

import com.example.employeecontrol.model.District;
import com.example.employeecontrol.model.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class RegionAppropriateDistrict {
    private Region region;
    private District district;
    private String  message;
    private boolean success;

    public RegionAppropriateDistrict(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public RegionAppropriateDistrict(Region region, District district, boolean success) {
        this.region = region;
        this.district = district;
        this.success = success;
    }
}
