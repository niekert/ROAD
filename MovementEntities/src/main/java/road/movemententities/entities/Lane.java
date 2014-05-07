package road.movemententities.entities;

import javax.persistence.*;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class Lane
{

    @Id
    @GeneratedValue
    private int id;
    private String laneIdentifier;

    @ManyToOne
    private Edge edge;

    @Column(name = "LaneIndex")
    private int index;
    private float speed; // double?

    @Column(name = "LaneLength")
    private float length; // double?


    private String position;
    //TODO: Find a way to map vector

    public Lane(String id, int index)
    {
        this.laneIdentifier = id;
        this.index = index;
    }

    public Lane(){

    }

    public Lane(Edge edge, String id, int index, float speed, float length)
    {
        this.edge = edge;
        this.laneIdentifier = id;
        this.index = index;
        this.speed = speed;
        this.length = length;
    }

    public String getLaneIdentifier()
    {
        return this.laneIdentifier;
    }

    public int getIndex()
    {
        return this.index;
    }

    public void setSpeed(Float speed)
    {
        this.speed = speed;
    }

    public void setLength(Float length)
    {
        this.length = length;
    }
}