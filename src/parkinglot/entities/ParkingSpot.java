package parkinglot.entities;

import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.VehicleSize;

public class ParkingSpot {
	private final String spotId;
	private boolean isOccupied;
	private Vehicle parkedVehicle;
	private final VehicleSize spotSize;
	
	
	public ParkingSpot(String spotId, VehicleSize spotSize) {
		this.spotId = spotId;
		this.spotSize = spotSize;
	}
	
	public String getSpotId() {
		return this.spotId;
	}
	
	public VehicleSize getSpotSize() {
		return this.spotSize;
	}
	
	public boolean getIsOccupied() {
		return this.isOccupied;
	}
	
	public Vehicle getParkedVehicle() {
		return this.parkedVehicle;
	}
	
	public synchronized void parkVehicle(Vehicle vehicle) {
		this.parkedVehicle = vehicle;
		this.isOccupied = true;
	}
	
	public synchronized void unParkVehicle() {
		this.parkedVehicle = null;
		this.isOccupied = false;
	}
	
	public boolean canVehicleFit(Vehicle vehicle) {
		if (isOccupied) return false;
		switch (vehicle.getSize()) {
		case SMALL:
			return this.spotSize == VehicleSize.SMALL;
		case MEDIUM:
			return this.spotSize == VehicleSize.MEDIUM || this.spotSize == VehicleSize.LARGE;
		case LARGE:
			return this.spotSize == VehicleSize.LARGE;
		default:
			return false;
		}
	}
	
}
