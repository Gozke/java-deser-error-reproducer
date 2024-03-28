package edu.gozke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;

public class SerializationTest {

	
	@Test
	public void deserializationFailsIfAirportIsTheRoot() throws ClassNotFoundException, IOException {
		City city = new City("BB");
		Airport airport = new Airport("BB");
		city.addAirport(airport);
		
		// Result is intentionally not used
		// It is the side effect that's important
		new AirportData(airport, city, "dummyKey");

		writeToFile(airport, "airport");
		Object object = readObject("airport");
		
		System.out.println(object.hashCode());
	}
	
	@Test
	public void deserializationIsOKIfAirportDataIsTheRoot() throws ClassNotFoundException, IOException {
		City city = new City("BB");
		Airport airport = new Airport("BB");
		city.addAirport(airport);
		AirportData apData = new AirportData(airport, city, "dummyKey");

		writeToFile(apData, "airportData");
		Object object = readObject("airportData");
		
		System.out.println(object.hashCode());
	}
	

	private static final Object readObject(String fileName) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			return ois.readObject();
		}
	}

	private static void writeToFile(Object obj, String fileName) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(obj);
		}
	}
}
