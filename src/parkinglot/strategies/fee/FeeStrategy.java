package parkinglot.strategies.fee;

import parkinglot.entities.ParkingTicket;

public interface FeeStrategy {
	double calculateFee(ParkingTicket parkingTicket);
}
