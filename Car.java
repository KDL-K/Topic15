package com.shevlik;

import java.time.Instant;

public class Car implements Runnable {
    private static int ID=0;
    private int id;
    private long NANOS_PER_SEC=1_000_000_000L;
    private long MILLIS_PER_SEC=1_000L;
    private int necessaryTimeOfParking;
    private int possibleTimeToWaitFreePlace;
    private int numberOfParkingPlace;
    private Parking parking;
    private Thread th;


    Car(Parking parking){
        id=++ID;
        this.necessaryTimeOfParking=(int)(Math.random()*20+10);
        this.possibleTimeToWaitFreePlace=(int)(Math.random()*10);
        this.parking=parking;
        th=new Thread(this,"Car_"+id);

        th.start();
    }

    @Override
    public void run() {
        long start=Instant.now().getNano();
        boolean free=false;
        synchronized (parking) {
            while ((Instant.now().getNano() - start) <= (possibleTimeToWaitFreePlace * NANOS_PER_SEC) && !free) {
                free = toPark(parking);
            }
        }
        if(free) {
            System.out.println("Car "+id+", number of parking: "+numberOfParkingPlace+", time of parking: "+necessaryTimeOfParking);
            try {
                System.out.println("Car "+id+" has parked.");
                Thread.sleep(necessaryTimeOfParking * MILLIS_PER_SEC);
                System.out.println("Car "+id+" begins to leave.");
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Car "+id+parking.freePlace(numberOfParkingPlace));
        }else{
            System.out.println("Car "+id+" can't wait more. It's leaving.");
        }


    }

    public boolean toPark(Parking parking){
        numberOfParkingPlace=parking.getNumberOfFreePlace();
        if(numberOfParkingPlace>=0){
            parking.takePlace(numberOfParkingPlace);
            return true;
        }
        return false;
    }

    public Thread getTh() {
        return th;
    }
}
