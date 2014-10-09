http://stackoverflow.com/questions/15202997/what-is-the-difference-between-canonical-name-simple-name-and-class-name-in-jav

  //primitive
  System.out.println(int.class.getName());
  System.out.println(int.class.getCanonicalName());
  System.out.println(int.class.getSimpleName());
  
  System.out.println();
  
  //class
  System.out.println(String.class.getName());
  System.out.println(String.class.getCanonicalName());
  System.out.println(String.class.getSimpleName());
  
  System.out.println();
  
  //inner class
  System.out.println(HashMap.SimpleEntry.class.getName());
  System.out.println(HashMap.SimpleEntry.class.getCanonicalName());
  System.out.println(HashMap.SimpleEntry.class.getSimpleName());        
  
  System.out.println();
  
  //anonymous inner class
  System.out.println(new Serializable(){}.getClass().getName());
  System.out.println(new Serializable(){}.getClass().getCanonicalName());
  System.out.println(new Serializable(){}.getClass().getSimpleName());

Output
---

  int
  int
  int
  
  java.lang.String
  java.lang.String
  String
  
  java.util.AbstractMap$SimpleEntry
  java.util.AbstractMap.SimpleEntry
  SimpleEntry
  
  ClassnameTest$1
  null
