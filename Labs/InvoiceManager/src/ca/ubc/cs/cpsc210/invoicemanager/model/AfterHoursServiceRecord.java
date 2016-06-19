package ca.ubc.cs.cpsc210.invoicemanager.model;

/**
 * Created by Leonard on 2016/3/7.
 */
public class AfterHoursServiceRecord extends AbstractServiceRecord {
        public static int AFTER_HOURS_CALLOUT = 120;
        public static int AFTER_HOURS_SERVICE_HOURLY = 100;
        public static int AFTER_HOURS_SERVICEPTS_BASE = 5;
        public static int AFTER_HOURS_SERVICEPTS_HOURLY = 1;

        public AfterHoursServiceRecord (int hours) {
            super(hours, ServiceType.AFTER_HOURS);
            this.recordID = ++ nextRecordID;
            buildInvoice();
        }

        // EFFECTS: returns number of service points earned with this service record
        @Override
        public int getServicePoints(){
            return AFTER_HOURS_SERVICEPTS_BASE + hours * AFTER_HOURS_SERVICEPTS_HOURLY;
        }

        @Override
        // EFFECTS: returns callout fee in $ for this service record
        public int getCalloutFee() {
            return AFTER_HOURS_CALLOUT;
        }

        @Override
        // EFFECTS: returns service fee in $ for this service record
        public int getServiceFee(){
            return AFTER_HOURS_SERVICE_HOURLY * hours;
        }
}

