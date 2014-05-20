package road.movementservice;

import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;
import road.movemententityaccess.dao.*;
import road.movementservice.mapper.DtoMapper;
import road.movementservice.servers.BillServer;
import road.movementservice.servers.CarServer;
import road.movementservice.servers.DriverServer;
import road.movementservice.servers.PoliceServer;
import road.userservice.UserDAO;
import road.userservice.UserDAOImpl;
import road.userservice.exceptions.UserSystemException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.GregorianCalendar;

/**
 * Created by geh on 8-5-14.
 */
public class Server
{
    private LaneDAO laneDAO;
    private EdgeDAO edgeDAO;
    private CityDAO cityDAO;
    private VehicleDAO vehicleDAO;
    private ConnectionDAO connectionDAO;
    private InvoiceDAO invoiceDAO;
    private MovementDAO movementDAO;

    private DriverServer driverServer;
    private BillServer billServer;
    private PoliceServer policeServer;
    private CarServer carServer;

    private DtoMapper dtoMapper;

    /**
     * The user manager which is used to process all authentication requests.
     */
    private UserDAO userManager;

    /**
     * this method is used to initialize all the different services.
     */
    public void init()
    {
        EntityManagerFactory emfUserService = Persistence.createEntityManagerFactory("UserServicePUMS");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovementPUNonJTA");

        this.userManager = new UserDAOImpl(emfUserService);
        
        // Create a user for debugging.
        try {
            this.userManager.register("admin", "aidas123");
        } catch (UserSystemException e) {
            e.printStackTrace();
        }

        this.dtoMapper = new DtoMapper();
        this.laneDAO = new LaneDAOImpl(emf);
        this.edgeDAO = new EdgeDAOImpl(emf);
        this.cityDAO = new CityDAOImpl(emf);
        this.vehicleDAO = new VehicleDAOImpl(emf);
        this.connectionDAO = new ConnectionDAOImpl(emf);
        this.invoiceDAO = new InvoiceDAOImpl(emf);
        this.movementDAO  = new MovementDAOImpl(emf);

        this.driverServer = new DriverServer(this.userManager, this.laneDAO, this.connectionDAO, this.edgeDAO, this.vehicleDAO, this.invoiceDAO, this.dtoMapper);
        this.driverServer.init();

        this.billServer = new BillServer(this.invoiceDAO, this.userManager, this.movementDAO, this.cityDAO, this.dtoMapper);
        this.billServer.init();

        this.policeServer = new PoliceServer();
        this.policeServer.init();

        this.carServer = new CarServer(new EntityDAOImpl(emf));
        this.carServer.init();

        this.fillDatabase(emf);
    }

    /**
     * Function to fill the database with test data.
     * @param emf the entity manager factory used for getting the {@link EntityManager}.
     */
    private void fillDatabase(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        Vehicle v = new Vehicle();
        v.setLicensePlate("AA-12-BB");
        VehicleOwnership vo = new VehicleOwnership(v, 1, new GregorianCalendar(), null);

        em.persist(v);
        em.persist(vo);
    }
}
