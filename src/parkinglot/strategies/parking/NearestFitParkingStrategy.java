package parkinglot.strategies.parking;

import java.util.List;
import java.util.Optional;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.vehicle.Vehicle;

public class NearestFitParkingStrategy implements ParkingStrategy {

	@Override
	public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
		Optional<ParkingSpot> bestSpot = Optional.empty();
		for (ParkingFloor floor : floors) {
			Optional<ParkingSpot> availableSpot = floor.findAvailableSpot(vehicle);
			if (availableSpot.isPresent()) {
				return availableSpot;
			}
		}
		return bestSpot;
	}
	
}
