/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package road.policesystem.service;

import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.StolenCarDto;
import road.movementdtos.dtos.VehicleDto;
import road.userservice.dto.UserDto;
import road.policedts.connections.PoliceClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mitch
 */
@ApplicationScoped
public class PoliceService implements Serializable
{
    //@Inject @ProducerQualifier
    PoliceClient policeClient;
    
    @PostConstruct
    private void init()
    {
        policeClient = new PoliceClient();
        policeClient.start();
    }
    
    public MovementUserDto login(String username, String password)
    {
        return policeClient.authenticate(username, password);
    }

    public StolenCarDto getStolenCarByLicensePlate(String licensePlate) {
        return policeClient.getStolenCarByLicensePlate(licensePlate);
    }

    public StolenCarDto getStolenCarByCartrackerId(String cartrackerId) {
        return policeClient.getStolenCarByCartrackerId(cartrackerId);
    }

    public VehicleDto getVehicleByLicensePlate(String licensePlate) {
        return policeClient.getVehicleByLicensePlate(licensePlate);
    }

    public VehicleDto getVehicleByCartrackerId(String cartrackerId) {
        return policeClient.getVehicleByCartrackerId(cartrackerId);
    }

    public List<StolenCarDto> getAllStolenCars() {
        return policeClient.getAllStolenCars();
    }

    public boolean setStolen(VehicleDto vehicle, boolean isStolen) {
        return policeClient.setStolen(vehicle, isStolen);
    }
}
