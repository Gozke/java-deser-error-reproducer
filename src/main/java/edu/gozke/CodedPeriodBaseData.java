package edu.gozke;


public abstract class CodedPeriodBaseData extends BaseCodeObject
{
   private final String effectivePeriod;

   protected final CodedBaseDataContainer container;

   protected CodedPeriodBaseData(CodedBaseDataContainer container, String effectivePeriod)
   {
      super(container.getCode());

      assert (effectivePeriod != null);
      assert (container != null);

      this.container = container;
      this.effectivePeriod = effectivePeriod;
   } 
   
   public String getEffectivePeriod()
   {
      return effectivePeriod;
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == this)
      {
         return true;
      } 

      if (o == null)
      {
         return false;
      } 

      if (!(o instanceof CodedPeriodBaseData))
      {
         return false;
      }

      CodedPeriodBaseData data = (CodedPeriodBaseData) o;
      return code.equals(data.code) && effectivePeriod.equals(data.effectivePeriod);
   }

   @Override
   public int hashCode()
   {
      if (hash == 0)
      {
         hash = 17;
         hash = 37 * hash + code.hashCode();
         hash = 37 * hash + effectivePeriod.hashCode();
      }

      return hash;
   }

   @Override
   public int compareTo(Object obj)
   {
      CodedPeriodBaseData data = (CodedPeriodBaseData) obj;
      int cmpCode = code.compareTo(data.code);
      if (cmpCode != 0)
      {
         return cmpCode;
      }

      return effectivePeriod.compareTo(data.effectivePeriod);
   }
}
