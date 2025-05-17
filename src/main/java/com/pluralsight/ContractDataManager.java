package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContractDataManager {

    public static void saveContract(Contract contract) {
        try (BufferedReader br = new BufferedReader(new FileReader("contracts.csv"))) {


            if (contract instanceof SalesContract) {
                SalesContract sales = (SalesContract) contract;


            } else if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
