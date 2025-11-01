package parkinglot;

import java.util.Optional;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.ParkingTicket;
import parkinglot.strategies.fee.VehicleBasedFeeStrategy;
import parkinglot.vehicle.Bike;
import parkinglot.vehicle.Car;
import parkinglot.vehicle.Truck;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleSize;

public class ParkingLotDemoRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ParkingLot parkingLot = ParkingLot.getInstance();
		ParkingFloor floor1 = new ParkingFloor(1);
		
		floor1.addSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
		floor1.addSpot(new ParkingSpot("F1-M1", VehicleSize.MEDIUM));
		floor1.addSpot(new ParkingSpot("F1-L1", VehicleSize.LARGE));
		
		ParkingFloor floor2 = new ParkingFloor(2);
		
		floor2.addSpot(new ParkingSpot("F2-L1", VehicleSize.LARGE));
		floor2.addSpot(new ParkingSpot("F2-L2", VehicleSize.LARGE));
		floor2.addSpot(new ParkingSpot("F2-M1", VehicleSize.MEDIUM));
		
		parkingLot.addFloor(floor1);
		parkingLot.addFloor(floor2);
		
		parkingLot.setFeeStrategy(new VehicleBasedFeeStrategy());
		
		Vehicle bike = new Bike("B-123");
		Vehicle car = new Car("C-456");
		Vehicle truck = new Truck("T-789");
		
		Optional<ParkingTicket> bikeTicket = parkingLot.parkVehicle(bike);
		Optional<ParkingTicket> carTicket = parkingLot.parkVehicle(car);
		Optional<ParkingTicket> truckTicket = parkingLot.parkVehicle(truck);
		
		bikeTicket.get().printTicket();;
		
		floor1.displayAvailability();
		floor2.displayAvailability();
		
		
		if (carTicket.isPresent()) {
			Optional<Double> carFee = parkingLot.unParkVehicle(car.getLicenseNumber());
			carFee.ifPresent(fee -> System.out.printf("%s parking fee is %.2f\n", car.getLicenseNumber(), fee));
		}
//		
		floor1.displayAvailability();
		floor2.displayAvailability();
		
		
	}

}
