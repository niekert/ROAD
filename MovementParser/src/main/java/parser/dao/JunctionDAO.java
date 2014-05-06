package parser.dao;

import road.movemententities.entities.Junction;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface JunctionDAO
{

    /**
     *
     * @return The number of junctions in the database
     */
    int count();

    /**
     * Persist a junction in the database
     * @param junction The Junction object to persist
     */
    void create(Junction junction);

    /**
     * Edit an existing junction
     * @param junction The edited Junction object
     */
    void edit(Junction junction);

    /**
     * Remove a junction from the database
     * @param junction The Junction object to remove
     */
    void remove(Junction junction);



}
