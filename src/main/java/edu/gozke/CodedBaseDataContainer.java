package edu.gozke;

import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeMap;


public abstract class CodedBaseDataContainer extends BaseCodeObject
{
   private final static PeriodComparator COMPARATOR = new PeriodComparator();

   protected final TreeMap dataMap = new TreeMap(COMPARATOR);

   protected CodedBaseDataContainer(String code)
   {
      super(code);
   }

   protected final void addBaseData(CodedPeriodBaseData baseData)
   {
      dataMap.put(baseData.getEffectivePeriod(), baseData);
   }

   private static class PeriodComparator implements Comparator, Serializable
   {

      public int compare(Object arg0, Object arg1)
      {
         String period1 = (String) arg0;
         String period2 = (String) arg1;

         return period1.compareTo(period2);
      }
   }
}