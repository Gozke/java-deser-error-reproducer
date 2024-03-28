package edu.gozke;


public class Airport extends CodedBaseDataContainer
{

   Airport(String iataAirportCode)
   {
      super(iataAirportCode);
   }

   void addAirportData(AirportData data)
   {
      addBaseData(data);
   }

}
