package ca.ubc.cs.cpsc210.invoicemanager.model;

/**
 * Created by Leonard on 2016/3/7.
 */
public class DiscountServiceRecord extends AbstractServiceRecord {
    public static int DISCOUNT_CALLOUT = 0;
    public static int DISCOUNT_SERVICE_HOURLY = 80;
    public static int DISCOUNT_SERVICEPTS_BASE = 0;
    public static int DISCOUNT_SERVICEPTS_HOURLY = 0;

    public DiscountServiceRecord (int hours) {
        super(hours, ServiceType.DISCOUNT);
        this.recordID = ++ nextRecordID;
        buildInvoice();
    }

    // EFFECTS: returns number of service points earned with this service record
    @Override
    public int getServicePoints(){
        return DISCOUNT_SERVICEPTS_BASE + hours * DISCOUNT_SERVICEPTS_HOURLY;
    }

    @Override
    // EFFECTS: returns callout fee in $ for this service record
    public int getCalloutFee() {
        return DISCOUNT_CALLOUT;
    }

    @Override
    // EFFECTS: returns service fee in $ for this service record
    public int getServiceFee(){
        return DISCOUNT_SERVICE_HOURLY * hours;
    }
}
