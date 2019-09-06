package com.shevlik;

import java.util.Arrays;

public class Parking {
    private final int COUNT_OF_ALL_PLACES=5;
    private boolean[] parkingArray;

    public Parking(){
        parkingArray=new boolean[COUNT_OF_ALL_PLACES];
        for (int i=0;i<COUNT_OF_ALL_PLACES;i++){
            parkingArray[i]=true;
        }
    }

    public int getNumberOfFreePlace() {
        for (int i = 0; i < COUNT_OF_ALL_PLACES; i++) {
            if (parkingArray[i]) {
                return i;
            }
        }
        return -1;
    }

    public void takePlace(int numberOfFreePlace){
        parkingArray[numberOfFreePlace]=false;
    }

    public String freePlace(int numberOfPlace){
        parkingArray[numberOfPlace]=true;
        return " has left parking.";
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parkingArray=" + Arrays.toString(parkingArray) +
                '}';
    }
}
