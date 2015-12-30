package org.naveen.testprograms;

import java.util.Map;
import java.util.TreeMap;

public enum TemplateTypeEnum {
      INTERNAL_TEMPLATE(1),INTERNAL_PARTY_DOCUMENT(2),EXTERNAL_PARTY_PAPER(3);

  
  private final int templateTypeEnum;

  TemplateTypeEnum(int templateTypeEnum) 
  { 
          this.templateTypeEnum = templateTypeEnum; 
  }
  
  public int getTemplateType() {
          return this.templateTypeEnum;
  }
  
  private static Map<Integer, TemplateTypeEnum> values = getTemplateTypeValues();
  
  public static Map<Integer, TemplateTypeEnum> getTemplateTypeValues() {
	  Map<Integer, TemplateTypeEnum> valuesMap = new TreeMap<>();
	  for (TemplateTypeEnum e : values()) {
		  valuesMap.put(e.templateTypeEnum, e);
	  }
	  return valuesMap;
  }

  public static String getTemplateType(int index) {
	  TemplateTypeEnum e = values.get(index);
	  if (e != null) {
		  return e.toString();
	  }
          try {
                  return TemplateTypeEnum.values()[index].toString();

          } 
          catch (RuntimeException ex) {
                  return null;
          }
  }
}