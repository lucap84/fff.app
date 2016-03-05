package it.fff.business.common.comparator;

import java.util.Comparator;

import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.util.DistanceCalculator;

public class PlaceComparator implements Comparator<PlaceBO>{
	
	private double gpsLatTarget;
	private double gpsLongTarget;
	
	public PlaceComparator(){}
	
	public PlaceComparator(double gpsLatTarget, double gpsLongTarget){
		this.gpsLatTarget = gpsLatTarget;
		this.gpsLongTarget = gpsLongTarget;
	}

	@Override
	public int compare(PlaceBO p1, PlaceBO p2) {
		
		double gpsLatP1 = p1.getGpsLat();
		double gpsLongP1 = p1.getGpsLong();
		
		double gpsLatP2 = p2.getGpsLat();
		double gpsLongP2 = p2.getGpsLong();
		
		double dinstanceKmP1toTarget = DistanceCalculator.calculateDistance(gpsLatP1, gpsLongP1, gpsLatTarget, gpsLongTarget, "K");
		double dinstanceKmP2toTarget = DistanceCalculator.calculateDistance(gpsLatP2, gpsLongP2, gpsLatTarget, gpsLongTarget, "K");
		
		double	diff = dinstanceKmP1toTarget-dinstanceKmP2toTarget;
		
		return (int)diff;
	}

	public double getGpsLatTarget() {
		return gpsLatTarget;
	}

	public void setGpsLatTarget(double gpsLatTarget) {
		this.gpsLatTarget = gpsLatTarget;
	}

	public double getGpsLongTarget() {
		return gpsLongTarget;
	}

	public void setGpsLongTarget(double gpsLongTarget) {
		this.gpsLongTarget = gpsLongTarget;
	}

}
