package ca.ubc.cs.cpsc210.invoicemanager.model;

/**
 * Created by Leonard on 2016/3/7.
 */
public class EmergencyServiceRecord extends AbstractServiceRecord{

    public static int EMERG_CALLOUT = 150;
    public static int EMERG_SERVICE_HOURLY = 100;
    public static int EMERG_SERVICEPTS_BASE = 0;
    public static int EMERG_SERVICEPTS_HOURLY = 0;

    public EmergencyServiceRecord (int hours) {
        super(hours, ServiceType.EMERGENCY);
        this.recordID = ++ nextRecordID;
        buildInvoice();
    }

    // EFFECTS: returns number of service points earned with this service record
    @Override
    public int getServicePoints(){
        return EMERG_SERVICEPTS_BASE + hours * EMERG_SERVICEPTS_HOURLY;
    }

    @Override
    // EFFECTS: returns callout fee in $ for this service record
    public int getCalloutFee() {
        return EMERG_CALLOUT;
    }

    @Override
    // EFFECTS: returns service fee in $ for this service record
    public int getServiceFee(){
        return EMERG_SERVICE_HOURLY * hours;
    }

}
