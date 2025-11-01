package parkinglot.entities;

import java.util.Date;
import java.util.UUID;

import parkinglot.vehicle.Vehicle;

public class ParkingTicket {
	
	private final String ticketId;
	private final String parkingLotId;
	private final Vehicle vehicle;
	private final ParkingSpot parkingSpot;
	private final long entryTimeStamp;
	private long exitTimeStamp;
	
	public ParkingTicket(String parkingLotId, Vehicle vehicle, ParkingSpot parkingSpot) {
		this.parkingLotId = parkingLotId;
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
	
	public void printTicket() {
		System.out.println("Parking ticket details ---->");
		System.out.println("==== Parking Lot ID - " + this.parkingLotId);
		System.out.println("==== Ticket ID - " + this.ticketId);
		System.out.println("==== Parking Spot - " + this.parkingSpot.getSpotId());
		System.out.println("==== Entry TimeStamp - " + new Date(entryTimeStamp).toString());
	}

}
