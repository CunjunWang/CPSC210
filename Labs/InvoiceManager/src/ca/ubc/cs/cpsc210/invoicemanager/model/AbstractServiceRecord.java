package ca.ubc.cs.cpsc210.invoicemanager.model;

/**
 * Created by Leonard on 2016/3/3.
 */
public abstract class AbstractServiceRecord implements Comparable<AbstractServiceRecord>{
    protected static int nextRecordID = 0;
    protected ServiceType serviceType;
    protected int hours;
    protected int recordID;
    private Invoice invoice;

    public AbstractServiceRecord(int hours, ServiceType serviceType) {
        this.hours = hours;
        this.serviceType = serviceType;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getHours() {
        return hours;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    // EFFECTS: returns number of service points earned with this service record
    public abstract int getServicePoints();

    // EFFECTS: returns callout fee in $ for this service record
    public abstract int getCalloutFee();

    // EFFECTS: returns service fee in $ for this service record
    public abstract int getServiceFee();

    // NOTE: this class has a natural ordering that is inconsistent with equals()
    public int compareTo(AbstractServiceRecord other) {
        return recordID - other.recordID;
    }

    // MODIFIES: this
    // EFFECTS:  create invoice for this service record
    protected void buildInvoice() {
        invoice = new Invoice(recordID, getCalloutFee(), getServiceFee(), hours);
    }

    public static AbstractServiceRecord createServiceRecord(ServiceType serviceType, int hours) {
        switch(serviceType) {
            case REGULAR:
                return new RegularServiceRecord(hours);
            case AFTER_HOURS:
                return new AfterHoursServiceRecord(hours);
            case EMERGENCY:
                return new EmergencyServiceRecord(hours);
            case DISCOUNT:
                return new DiscountServiceRecord(hours);
        }
        return null;  // shouldn't get here
    }

}
