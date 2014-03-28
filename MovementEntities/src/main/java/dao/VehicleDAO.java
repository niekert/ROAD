package dao;

import entities.Vehicle;

import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface VehicleDAO
{
    /**
     * Persists a vehicle to the database
     * @param vehicle The Vehicle object to persist
     */
    void create(Vehicle vehicle);

    /**
     * Merges the changes of a modified Vehicle
     * @param vehicle The modified Vehicle object
     */
    void edit(Vehicle vehicle);

    /**
     * Removes a Vehicle from the database
     * @param vehicle The Vehicle object to remove.
     */
    void remove(Vehicle vehicle);

    /**
     * Find a vehicle by ID
     * @param id the ID of the vehicle
     * @return The found Vehicle object
     */
    Vehicle find(int id);

    /**
     * Find a vehicle by it's license plate
     * @param licensePlate The license plate number of the vehicle
     * @return The found vehicle
     */
    Vehicle findByLicensePlate(String licensePlate);

    /**
     * Get a list of all the vehicles currently belonging to a user
     * @param userID The ID of the user
     * @return List of owned vehicles
     */
    List<Vehicle> getVehiclesFromUser(int userID);

    /**
     * Change the owner of a certain vehicle
     * @param vehicle The vehicle switching owners
     * @param userID The user ID of the new owner.
     */
    void changeVehicleOwnership(Vehicle vehicle, int userID);
}