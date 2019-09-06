package com.shevlik;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Parking parking=new Parking();
        for(int i=0;i<5;i++){
            Car car=new Car(parking);
        }
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        Car car1=new Car(parking);
        Car car2=new Car(parking);
        Car car3=new Car(parking);
        Car car4=new Car(parking);
        Car car5=new Car(parking);
        try{
            car1.getTh().join();
            car2.getTh().join();
            car3.getTh().join();
            car4.getTh().join();
            car5.getTh().join();
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Parking is empty.");
    }
}
