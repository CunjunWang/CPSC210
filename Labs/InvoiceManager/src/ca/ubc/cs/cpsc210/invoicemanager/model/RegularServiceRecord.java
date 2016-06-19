package ca.ubc.cs.cpsc210.invoicemanager.model;

/**
 * Created by Leonard on 2016/3/7.
 */
public class RegularServiceRecord extends AbstractServiceRecord{
    public static int REG_CALLOUT = 80;
    public static int REG_SERVICE_HOURLY = 80;
    public static int REG_SERVICEPTS_BASE = 10;
    public static int REG_SERVICEPTS_HOURLY = 2;

    public RegularServiceRecord(int hours) {
        super(hours, ServiceType.REGULAR);
        this.recordID = ++ nextRecordID;
        buildInvoice();
    }

    // EFFECTS: returns number of service points earned with this service record
    @Override
    public int getServicePoints(){
        return REG_SERVICEPTS_BASE + hours * REG_SERVICEPTS_HOURLY;
    }

    @Override
    // EFFECTS: returns callout fee in $ for this service record
    public int getCalloutFee() {
        return REG_CALLOUT;
    }

    @Override
    // EFFECTS: returns service fee in $ for this service record
    public int getServiceFee(){
        return REG_SERVICE_HOURLY * hours;
    }
}
