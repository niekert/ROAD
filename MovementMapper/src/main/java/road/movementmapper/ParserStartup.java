package road.movementmapper;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ejb.Singleton;

import org.xml.sax.SAXException;
import road.movemententities.entities.City;
import road.movemententities.entities.CityDistance;
import road.movemententities.entities.Invoice;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleInvoice;
import road.movemententities.entities.VehicleOwnership;
import road.movemententities.entities.enumerations.PaymentStatus;
import road.movementmapper.dao.EntityDAO;

import road.movementparser.injectable.MovementParser;
import test.TestSumoParser;

@Startup
@Singleton
public class ParserStartup {
    /* Initial map files */
    private static final String INPUTOSMFILE = "PTS-ESD-2.osm";
    
    /* SUMO file should be generated from the osm file */
    private static final String INPUTSUMOFILE = "PTS-ESD-2.net.xml";
    
    /* Path to directory with initial movements */
//    private static final String MOVEMENTSDIR = "/home/tijs/Downloads/verpl_systeem/";
    
    /* SUMO movements file, containing the vehicle movements over time */
    private static final String MOVEMENTSSMALLFILE = "verplaatsingen_20110209_small.xml";
    
    @Inject
    private MovementMapper mapParser;
    
    @Inject
    private MovementParser movementParser;
    
    @PostConstruct
    public void init() {
        initialiseMap();
        parseNewMovements();
    }
    
    /**
     * Parse the initial map of roads and cities.
     */
    public void initialiseMap() {
        // The SUMO file must be converted from the OSM!
        File inputSUMO = getResourceFile(INPUTSUMOFILE);
        File inputOSM = getResourceFile(INPUTOSMFILE);
        
        System.out.println("[PARSERSERVICE] Parsing map "+inputSUMO.getAbsolutePath());
        try {
            mapParser.parseSUMO(inputSUMO);
            mapParser.parseOSM(inputOSM);
            System.out.println("[PARSERSERVICE] Finished parsing initial map");
        } catch (SAXException ex) {
            Logger.getLogger(TestSumoParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private File getResourceFile(String filename) {
        URL url = this.getClass().getResource("/"+filename);
        System.out.println("url: "+url.getPath());
        return new File(url.getFile());
    }
    
    
    /**
     * Parse all xml movement files in directory DIR
     * When parsing completed, delete the files
     */
    //@Schedule(minute = "*/3", hour = "*")
    public void parseNewMovements() {
    	System.out.println("[PARSERSERVICE] Parsing movements...");
//        CODE TO LOAD INITIAL MOVEMENTS (TOO HEAVY FOR NOW)
//        System.out.println("[SCHEDULE] Parsing new movements in directory "+new File(MOVEMENTSDIR).getAbsolutePath());
//        Calendar cal = Calendar.getInstance();
//        for(int dd=7;dd<7;dd++) 
//        {
//            cal.set(2011, 2, 9);
//            movementParser.parseChanges(new File(MOVEMENTSDIR+"verplaatsingen_2011020"+dd+".xml"), cal);
//        }
//      
        // Set parsing time to midnight
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 2, 9, 0, 0, 0);
        movementParser.parseChanges(new File(MOVEMENTSSMALLFILE), cal);
    }
    
    @Inject
    private EntityDAO entityDAO;
    
    /**
     * Generate 1 example invoice
     */
    public void generateTestData()
    {
        int userID = 1;
        String vehicleID = "t0";

        // Create vehicle ownership of car t0
        GregorianCalendar registerdate = new GregorianCalendar();
        registerdate.add(Calendar.YEAR, -3);
        GregorianCalendar expdate = new GregorianCalendar();
        expdate.add(Calendar.YEAR, 3);
        VehicleOwnership vehicleOwnership = new VehicleOwnership();
        vehicleOwnership.setContributeGPSData(true);
        vehicleOwnership.setRegistrationdate(registerdate);
        vehicleOwnership.setRegistrationExperationDate(expdate);
        vehicleOwnership.setUserID(userID);
        Vehicle t0 = (Vehicle) entityDAO.findById(Vehicle.class, vehicleID);
        if (t0 == null)
        {
            t0 = new Vehicle("t0");
            entityDAO.create(t0);
        }
        vehicleOwnership.setVehicle(t0);
        entityDAO.create(vehicleOwnership);

        // Generate vehicle city visits
        List<CityDistance> cityDistances = new ArrayList();
        City woenselnoord = (City) entityDAO.findById(City.class, "-8");
        cityDistances.add(new CityDistance(woenselnoord, 40.0, 1.25));
        City geldrop = (City) entityDAO.findById(City.class, "-40");
        cityDistances.add(new CityDistance(geldrop, 10.0, 0.25));
        City tilburg = (City) entityDAO.findById(City.class, "-10");
        cityDistances.add(new CityDistance(tilburg, 30.0, 2.25));
        entityDAO.create(vehicleOwnership);

        // Generate VehicleInvoices
        VehicleInvoice vehicleInvoice = new VehicleInvoice();
        vehicleInvoice.setOwnership(vehicleOwnership);
        vehicleInvoice.setMovementList(cityDistances);
        entityDAO.create(vehicleInvoice);

        // Generate invoice
        Calendar startdate = Calendar.getInstance();
        startdate.add(Calendar.YEAR, -3);
        Calendar enddate = Calendar.getInstance();
        enddate.add(Calendar.YEAR, 3);
        Calendar gendate = Calendar.getInstance();
        gendate.set(2013, 04, 1);
        Invoice invoice = new Invoice();
        invoice.setStartDate(startdate.getTime());
        invoice.setEndDate(enddate.getTime());
        invoice.setPaymentStatus(PaymentStatus.NOT_PAYED);
        invoice.setGenerationDate(gendate.getTime());
        entityDAO.create(invoice);
    }

}
