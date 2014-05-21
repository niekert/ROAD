package road.billdts.connections;

import road.billdts.dto.InvoiceSearchQuery;
import road.movementdtos.dtos.CityDistanceDto;
import road.movementdtos.dtos.MovementUserDto;
import road.movementdts.connections.QueueClient;
import road.movementdtos.dtos.CityDto;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;
import road.movementdts.connections.MovementConnection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by geh on 11-4-14.
 * This is the connection that should be used by the BillSystem. One can call remotecall.
 * This class is NOT THREADSAFE. If you want multithreading, create on BillConnection for EACH
 * thread.
 */
public class BillClient extends QueueClient implements IBillQuery
{
    public BillClient()
    {
        super("localhost:1099", MovementConnection.FactoryName, MovementConnection.BillSystemQueue);
    }

    @Override
    public MovementUserDto authenticate(String user, String password)
    {
        return this.remoteCall("authenticate", MovementUserDto.class, user, password);
    }

    @Override
    public boolean adjustKilometerRate(CityDto city, Date addDate, String price)
    {
        return this.remoteCall("adjustKilometerRate", boolean.class, city, addDate, price);
    }

    @Override
    public List<CityDto> getCities()
    {
        return this.remoteCall("getCities", ArrayList.class);
    }

    @Override
    public Integer generateMonthlyInvoices(Integer month, Integer year)
    {
        Integer result = this.remoteCall("generateMonthlyInvoices", Integer.class, month, year);
        System.out.println("Remote call result = " + result);
        return result;
    }

    @Override
    public List<InvoiceDto> getInvoicesForSearch(InvoiceSearchQuery searchDetails)
    {
        List<InvoiceDto> result = this.remoteCall("getInvoicesForSearch", ArrayList.class, searchDetails);
        return result;
    }

    @Override
    public Boolean updateInvoicePaymentStatus(Integer invoiceID, PaymentStatus paymentStatus)
    {
        Boolean success = this.remoteCall("updateInvoicePaymentStatus", Boolean.class, invoiceID, paymentStatus);
        return success;
    }

    @Override
    public InvoiceDto getInvoiceDetails(Integer invoiceID)
    {
        InvoiceDto foundDto = this.remoteCall("getInvoiceDetails", InvoiceDto.class, invoiceID);
        return foundDto;
    }

    @Override
    public List<CityDistanceDto> getCityDistances(Integer vehicleInvoiceID)
    {
        List<CityDistanceDto> cityDistanceDtoList = this.remoteCall("getCityDistances", ArrayList.class, vehicleInvoiceID);
        return cityDistanceDtoList;
    }
}
