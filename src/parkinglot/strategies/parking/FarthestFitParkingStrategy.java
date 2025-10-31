package parkinglot.strategies.parking;

import java.util.List;
import java.util.Optional;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.vehicle.Vehicle;

public class FarthestFitParkingStrategy implements ParkingStrategy {

	@Override
	public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
		// TODO Auto-generated method stub
		Optional<ParkingSpot> bestSpot = Optional.empty();
		for (ParkingFloor floor: floors.reversed()) {
			Optional<ParkingSpot> availableSpot = floor.findAvailableSpot(vehicle);
			if (availableSpot.isPresent()) {
				return availableSpot;
			}
		}
		return bestSpot;
	}

}
