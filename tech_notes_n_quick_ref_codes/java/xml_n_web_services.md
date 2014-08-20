Steps done to get started

1    Download glassfish.
2    Download and install 'Additional Server Adaptor' for glassfish. [From New server window, click that link (not worked) or from eclipse marketPlace (worked)]
3    Servers view would be if JEE is installed
4    Create new 'Dynamic Web Project', create target runtime as glassfish (if not present). Give path of extracted glassfish folder(from step 1)
5    Create simple class, add @WebService notation.
6    Run on server
7    Open browser, look at wsdl, xsd and tester
8    Test via soapUI
9    Write client code, new project
10  Use wsimport
11  Can add asynchronous code by making few changes. Async two approaches - polling and -callback

wsimport sample output

    C:\Users\Desktop\WS_client>wsimport -d src -keep -p ccy_client -b custom-client.xml http://localhost:8080/WS-proj1/CurrencyConverterServiceService?wsdl
    parsing WSDL...
    generating code...
    compiling code...

Server code

    package service;
 
    import javax.jws.WebService;
    import exceptions.CurrencyConversionException;
 
    @WebService
    public class CurrencyConverterService {
 
    public double convert(String from, String to, double amount)
            throws CurrencyConversionException {
         
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (from.equals("USD") && to.equals("INR")) {
            return amount * 50.0;
        }
        throw new CurrencyConversionException("Conversion from " + from
                + " to " + to + " not yet implemented!");
    }
    }
    
    
