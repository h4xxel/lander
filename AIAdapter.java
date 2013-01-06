/* lander
 * Copyright 2012-2013 Axel Isaksson
 * 
 * AI adapter interface for converting height, fuel and speed values to AI indecies
 */

public interface AIAdapter {
	public int calcFuelIndex(Ship ship);
	public int calcHeightIndex(Ship ship, Background background);
	public int calcSpeedIndex(Ship ship);
}
