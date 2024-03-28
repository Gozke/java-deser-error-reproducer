package edu.gozke;


public class AirportData extends CodedPeriodBaseData
{
   private final City city;
 
   AirportData(Airport airport, City city, String effectivePeriod)
   {
      super(airport, effectivePeriod);

      this.city = city;

      airport.addAirportData(this);
   }

}
