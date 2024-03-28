package edu.gozke;

import java.util.HashSet;
import java.util.Set;

public class City extends BaseCodeObject
{

   private final Set<Airport> airports = new HashSet<>();

   City(String iataCityCode)
   {
      super(iataCityCode);
   }

   public synchronized void addAirport(Airport airport)
   {
      airports.add(airport);
   }

}
