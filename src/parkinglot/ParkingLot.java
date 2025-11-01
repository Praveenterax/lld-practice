
package parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.ParkingTicket;
import parkinglot.strategies.fee.FeeStrategy;
import parkinglot.strategies.fee.FlatRateFeeStrategy;
import parkinglot.strategies.parking.BestFitParkingStrategy;
import parkinglot.strategies.parking.ParkingStrategy;
import parkinglot.vehicle.Vehicle;

// ParkingLot -> Singleton
// ParkingLot should have - 
// It should have Floors -> Floors - parkingSpots -> ParkingSpot - id, size, occupied
// It should have Vehicle -> Vehicle - Small, Large, Medium, LicenseNumber
// FeeStrategy
// ParkingStrategy
// Abilities - change strategies, parkVehicle, unParkVehicle
// Type of 
public class ParkingLot {
	private static ParkingLot instance;
	private final String parkingLotId;
	private final List<ParkingFloor> floors = new ArrayList<ParkingFloor>();
	private final Map<String, ParkingTicket> activeTickets;
	private FeeStrategy feeStrategy;
	private ParkingStrategy parkingStrategy;
	
	private ParkingLot() {
		this.parkingLotId = UUID.randomUUID().toString();
		this.feeStrategy = new FlatRateFeeStrategy();
		this.parkingStrategy = new BestFitParkingStrategy();
		this.activeTickets = new ConcurrentHashMap<String, ParkingTicket>();
	}
	
	public static ParkingLot getInstance() {
		if (instance == null) {
			instance = new ParkingLot();
		}
		return instance;
	}
	
	public void addFloor(ParkingFloor newFloor) {
		floors.add(newFloor);
	}
	
	public void setParkingStrategy(ParkingStrategy newParkingStrategy) {
		this.parkingStrategy = newParkingStrategy;
	}
	
	public void setFeeStrategy(FeeStrategy newFeeStrategy) {
		this.feeStrategy = newFeeStrategy;
	}
	
	public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
		Optional<ParkingSpot> availableSpot = this.parkingStrategy.findSpot(floors, vehicle);
		
		if (availableSpot.isPresent()) {
			ParkingSpot spot = availableSpot.get();
			spot.parkVehicle(vehicle);
			ParkingTicket parkingTicket = new ParkingTicket(parkingLotId, vehicle, spot);
			activeTickets.put(vehicle.getLicenseNumber(), parkingTicket);
			System.out.printf("%s parked at %s. Ticket: %s \n", vehicle.getLicenseNumber(), spot.getSpotId(), parkingTicket.getTicketId());
			return Optional.of(parkingTicket);
		}
		return Optional.empty();
		
	}
	
	public Optional<Double> unParkVehicle(String licenseNumber) {
		ParkingTicket ticket = activeTickets.remove(licenseNumber);
		if (ticket == null) {
			System.out.println("Ticket not found");
			return Optional.empty();
		}
		ticket.setExitTimeStamp();
		ticket.getParkingSpot().unParkVehicle();
		Double parkingFee = feeStrategy.calculateFee(ticket);
		return Optional.of(parkingFee);
	}
	
}
