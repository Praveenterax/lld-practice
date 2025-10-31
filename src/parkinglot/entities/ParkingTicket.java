package parkinglot.entities;

import java.util.Date;
import java.util.UUID;

import parkinglot.vehicle.Vehicle;

public class ParkingTicket {
	
	private final String ticketId;
	private final Vehicle vehicle;
	private final ParkingSpot parkingSpot;
	private final long entryTimeStamp;
	private long exitTimeStamp;
	
	public ParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
		this.ticketId = UUID.randomUUID().toString();
		this.vehicle = vehicle;
		this.parkingSpot = parkingSpot;
		this.entryTimeStamp = new Date().getTime();
	}
	
	public String getTicketId() {
		return this.ticketId;
	}
	
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	
	public ParkingSpot getParkingSpot() {
		return this.parkingSpot;
	}
	
	public long getEntryTimeStamp() {
		return this.entryTimeStamp;
	}
	
	public long getExitTimeStamp() {
		return this.exitTimeStamp;
	}
	
	public void setExitTimeStamp() {
		this.exitTimeStamp = new Date().getTime();
	}

}
