package com.pluralsight;

import java.io.*;

public class ContractDataManager {

    public static void saveContract(Contract contract) {

        //creating a file object
       File contractFile = new File("contracts.csv");
        //Checking if file exists
       boolean fileExists = contractFile.exists();
       //checking if file is empty
       boolean fileEmpty = !fileExists || contractFile.length() == 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            //if file is empty, write header row
            if (fileEmpty){
                writer.write("TYPE|DATE|NAME|EMAIL|VIN|MAKE|MODEL|TYPE|COLOR|ODOMETER|PRICE|SALES_TAX|RECORDING_FEE|FINANCE|MONTHLY_PAYMENT|ENDING_VALUE|LEASE_FEE\n");
            }
            //checking if contract is a SalesContract instance
            if (contract instanceof SalesContract) {
                SalesContract sales = (SalesContract) contract;
                //Formating data in pipe-separated format
                    writer.write("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f\n".formatted(
                            sales.getDate(),
                            sales.getCustomerName(),
                            sales.getCustomerEmail(),
                            sales.getVehicle().getVin(), sales.getVehicle().getYear(),
                            sales.getVehicle().getMake(), sales.getVehicle().getModel(),
                            sales.getVehicle().getVehicleType(), sales.getVehicle().getColor(),
                            sales.getVehicle().getOdometer(), sales.getVehicle().getPrice(),
                            sales.getSalesTaxAmount(), sales.getRecordingFee(), sales.getProcessingFee(),
                            sales.getTotalPrice(), sales.isFinanceOption() ? "Yes" : "No", sales.getMonthlyPayment()));

            } else if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;
                writer.write("LEASE|%s|%s|%s|%s|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f\n".formatted(
                        lease.getDate(),
                        lease.getCustomerName(),
                        lease.getCustomerEmail(),
                        lease.getVehicle().getVin(), lease.getVehicle().getYear(),
                        lease.getVehicle().getMake(), lease.getVehicle().getModel(),
                        lease.getVehicle().getVehicleType(), lease.getVehicle().getColor(),
                        lease.getVehicle().getOdometer(), lease.getVehicle().getPrice(),
                        lease.getExpectedEndingValue(), lease.getLeaseFee(), lease.getTotalPrice(),
                        lease.getMonthlyPayment()));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
