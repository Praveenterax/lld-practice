package parkinglot.entities;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleSize;

public class ParkingFloor {
	
	private int floorNumber;
	private Map<String, ParkingSpot> spots;
	
	public ParkingFloor(int floorNum) {
		this.floorNumber = floorNum;
		this.spots = new ConcurrentHashMap<String, ParkingSpot>();
	}
	
	public void addSpot(ParkingSpot spot) {
		this.spots.put(spot.getSpotId(), spot);
	}
	
	public synchronized Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle) {
		return this.spots.values().stream().filter(spot -> !spot.getIsOccupied() && spot.canVehicleFit(vehicle)).sorted(Comparator.comparing(ParkingSpot::getSpotSize)).findFirst();
	}
	
	public void displayAvailability() {
		System.out.printf("Floor Availability of Floor - %d \n", floorNumber);
		Map<VehicleSize, Long> availableCounts = this.spots.values().stream().filter(spot -> !spot.getIsOccupied()).collect(Collectors.groupingBy(ParkingSpot::getSpotSize, Collectors.counting()));
		for (VehicleSize size: VehicleSize.values()) {
			System.out.printf("Size - %s -> %d \n", size, availableCounts.get(size));
		}
	}

}
