
full class name - comment

<pre>
java.util.Date
 new Date();
 new Date(long millisecondsSince010170);

java.util.Calendar
 Abstract class
 Calendar.getInstance();
 Calendar.getInstance(Locale);                                                            

java.util.Locale
 Abstract class Locale.getDefault();
 new Locale(String language);
 new Locale(String language, String country);

java.text.DateFormat
 Abstract class DateFormat.getInstance();
 DateFormat.getDateInstance();
 DateFormat.getDateInstance(style);
 DateFormat.getDateInstance(style, Locale);
 
java.text.NumberFormat
 Abstract class NumberFormat.getInstance()
 NumberFormat.getInstance(Locale)
 NumberFormat.getNumberInstance()
 NumberFormat.getNumberInstance(Locale)
 NumberFormat.getCurrencyInstance()
 NumberFormat.getCurrencyInstance(Locale)

java.util.Queue<?>

</pre>

tricky points
- consutructor of an abstract class can be private
- 
Interfaces
- are implicitly abstract, you cannot apply `final`, `strictfp` or `native`
- methods are implicitly `public abstract`
- variables are implicitly `public static final`

Enums
- non-final static variable can't be called from constructor of enum, but can be indirectly called from a static method
- enum variables are implicitly `final` and `static`

Generics
- cannot perform `instanceof` check against a parameterized type due to type erasure.
- 
