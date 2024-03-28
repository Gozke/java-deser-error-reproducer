package edu.gozke;

import java.io.Serializable;


public abstract class BaseCodeObject implements Serializable, Comparable
{
   protected String code;

   protected int hash;

   protected BaseCodeObject(String code)
   {
      assert (code != null);
      this.code = code;
   }


   public final String getCode()
   {
      return code;
   }

   @Override
   public int compareTo(Object obj)
   {
      return code.compareTo(((BaseCodeObject) obj).code);
   }

   public boolean equals(Object otherObject)
   {
      if (otherObject == this)
      {
         return true;
      }

      if (otherObject == null)
      {
         return false;
      }

      if (!(otherObject instanceof BaseCodeObject))
      {
         return false;
      }

      BaseCodeObject otherBaseObject = (BaseCodeObject) otherObject;

      return code.equals(otherBaseObject.code);
   }

   @Override
   public int hashCode()
   {
      if (hash == 0)
      {
         hash = code.hashCode();
      }

      return hash;
   }
}
