package road.movemententityaccess.dao;

import road.movemententities.entities.Connection;

import javax.persistence.EntityManager;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface ConnectionDAO
{
    /**
     * Searches for a connection in the database
     * @param connectionID the ID of the connection
     * @return The connection found, or null.
     */
    Connection find(int connectionID);

	Long count();

    void setEntityManager(EntityManager em);
}