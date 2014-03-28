package dao;

import entities.Movement;

import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface MovementDAO
{
    /**
     * counts the number of Movements in the database
     * @return The number of movements
     */
    int count();

    /**
     * Persists a movement to the database
     * @param movement The movement object to persist
     */
    void create(Movement movement);

    /**
     * Saves the changes to an already existing movement to the database
     * @param movement The modified Movement object
     */
    void edit(Movement movement);

    /**
     * Removes a Movement from the database
     * @param movement The Movement object to remove
     */
    void remove(Movement movement);

    /**
     * Find a movement by ID
     * @param MovementID The ID of the movement
     * @return The found movement
     */
    Movement find(int MovementID);

    /**
     * Get all movements of a certain date
     * @param date movement date
     * @return List of found movements
     */
    List<Movement> getMovementsByDate(Date date);
}