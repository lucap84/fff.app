package it.fff.business.comparator;

import java.util.Comparator;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.PlaceBO;

public class PlaceDistanceComparator implements Comparator<EventBO> {
	
	private double rootGpsLat;
	private double rootGpsLong;
	
	public PlaceDistanceComparator(double rootGpsLat, double rootGpsLong) {
		this.rootGpsLat = rootGpsLat;
		this.rootGpsLong = rootGpsLong;
	}

	@Override
	public int compare(EventBO e1, EventBO e2) {
		PlaceBO p1 = e1.getLocation();
		PlaceBO p2 = e2.getLocation();
		
		double distance1FromRoot = this.distance(p1.getGpsLat(),p1.getGpsLong(), this.rootGpsLat, this.rootGpsLong);
		double distance2FromRoot = this.distance(p2.getGpsLat(),p2.getGpsLong(), this.rootGpsLat, this.rootGpsLong);
		
		double distanceDifference = distance1FromRoot - distance2FromRoot;
		
		return (int)distanceDifference;
	}
	
	private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(
            (x1 - x2) *  (x1 - x2) + 
            (y1 - y2) *  (y1 - y2)
        );
    }

}
