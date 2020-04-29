package com.evliion.ev.payload;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserVehicleResponse {
    private boolean success;
    private Long user_id;
    private List<VehicleRequest> vehicles;

    public boolean isSuccess() {
        return success;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<VehicleRequest> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleRequest> vehicles) {
        this.vehicles = vehicles;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

