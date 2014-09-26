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
    
*Client code can be written in many ways.*

default synchronous

to write asynchronous create client code with wsimport -b option that points to a custom-client.xml that has async turned on.

client_code

    package client_testers;
    import java.util.concurrent.Future;
    import javax.xml.ws.AsyncHandler;
    import javax.xml.ws.Response;
    import ccy_client.ConvertResponse;
    import ccy_client.CurrencyConverterService;
    import ccy_client.CurrencyConverterServiceService;
    public class CcyConvertClient {
    public static void main(String[] args) throws Exception {
        // Normal synchronous client. Call is blocked till we get a response. Server can take long.
        CurrencyConverterServiceService ccs = new CurrencyConverterServiceService();
        CurrencyConverterService csproxy = ccs
                .getCurrencyConverterServicePort();
    //        System.out.println("client got : " + csproxy.convert("USD", "INR", 11));
        // New Asynchronous call
        // async approach 1: polling
        System.out.println("Polling response method");
        Response<ConvertResponse> response = csproxy.convertAsync("USD", "INR", 12);
        System.out.println("Continuing....");
        while (true) {
            System.out.println("polling");
            Thread.sleep(1500);
            if (response.isDone()) {
                ConvertResponse data = response.get();
                System.out.println("" + data.getReturn());
                break;
            }
        }
        // async approach 2: callback
        // 2.1 annonymous class
        System.out.println("Callback method of getting async response");
        csproxy.convertAsync("USD", "INR", 13, new AsyncHandler<ConvertResponse>() {
             
            @Override
            public void handleResponse(Response<ConvertResponse> res) {
                System.out.println("In callback");
                try {
                    System.out.println(res.get().getReturn());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        class MyCallbackHandler implements AsyncHandler<ConvertResponse> {
            @Override
            public void handleResponse(Response<ConvertResponse> res) {
                System.out.println("Reeived callback...");
                try {
                    ConvertResponse data = res.get();
                    System.out.println(data.getReturn());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
         
        @SuppressWarnings({ "unchecked", "unused" })
        Future<ConvertResponse> response2 = (Future<ConvertResponse>) csproxy.convertAsync("USD", "INR", 14, new MyCallbackHandler());
        // just to hold the main thread
        System.in.read();
    }
    }
    
Exceptions/Error

Double quotes copy/paste error in custom binding file to turn on the async mode.

C:\Users\Desktop\WS_client>wsimport -d src -keep -p ccy_client -b custom-client.xml http://localhost:8080/WS-proj1/CurrencyConverterServiceService?wsdl
Exception in thread "main" com.sun.xml.internal.ws.streaming.XMLStreamReaderException: XML reader error: javax.xml.stream.XMLStreamException: ParseError at [row,col]:[7,13]
Message: Element type "bindings" must be followed by either attribute specifications, ">" or "/>".
        at com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.wrapException(XMLStreamReaderUtil.java:256)
        at com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.next(XMLStreamReaderUtil.java:84)
        at com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.nextContent(XMLStreamReaderUtil.java:99)
        at com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.nextElementContent(XMLStreamReaderUtil.java:89)
        at com.sun.tools.internal.ws.wscompile.WsimportOptions.parseBindings(WsimportOptions.java:417)
        at com.sun.tools.internal.ws.wscompile.WsimportTool.run(WsimportTool.java:160)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
        at java.lang.reflect.Method.invoke(Method.java:597)
        at com.sun.tools.internal.ws.Invoker.invoke(Invoker.java:105)
        at com.sun.tools.internal.ws.WsImport.main(WsImport.java:41)
Caused by: javax.xml.stream.XMLStreamException: ParseError at [row,col]:[7,13]
Message: Element type "bindings" must be followed by either attribute specifications, ">" or "/>".
        at com.sun.org.apache.xerces.internal.impl.XMLStreamReaderImpl.next(XMLStreamReaderImpl.java:594)
        at com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.next(XMLStreamReaderUtil.java:65)
        ... 10 more

Weird JAXB error, when the package name was changed from eclipse. Don't change package name, give it at wsimport cmdline only.

server down, client errored/exception aborted

Exception in thread "main" javax.xml.ws.WebServiceException: Failed to access the WSDL at: http://localhost:8080/WS-proj1/CurrentTimeService?wsdl. It failed with: 
    Connection refused: connect.
    at com.sun.xml.internal.ws.wsdl.parser.RuntimeWSDLParser.tryWithMex(RuntimeWSDLParser.java:151)
    at com.sun.xml.internal.ws.wsdl.parser.RuntimeWSDLParser.parse(RuntimeWSDLParser.java:133)
    at com.sun.xml.internal.ws.client.WSServiceDelegate.parseWSDL(WSServiceDelegate.java:254)
    at com.sun.xml.internal.ws.client.WSServiceDelegate.<init>(WSServiceDelegate.java:217)
    at com.sun.xml.internal.ws.client.WSServiceDelegate.<init>(WSServiceDelegate.java:165)
    at com.sun.xml.internal.ws.spi.ProviderImpl.createServiceDelegate(ProviderImpl.java:93)
    at javax.xml.ws.Service.<init>(Service.java:56)
    at curr_time_client.CurrentTimeService.<init>(CurrentTimeService.java:46)
    at client_testers.CurTimeClient.main(CurTimeClient.java:9)
Caused by: java.net.ConnectException: Connection refused: connect
    at java.net.PlainSocketImpl.socketConnect(Native Method)
    at java.net.PlainSocketImpl.doConnect(PlainSocketImpl.java:351)
    at java.net.PlainSocketImpl.connectToAddress(PlainSocketImpl.java:213)
    at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:200)
    at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:366)
    at java.net.Socket.connect(Socket.java:529)
    at java.net.Socket.connect(Socket.java:478)
    at sun.net.NetworkClient.doConnect(NetworkClient.java:163)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:395)
    at sun.net.www.http.HttpClient.openServer(HttpClient.java:530)
    at sun.net.www.http.HttpClient.<init>(HttpClient.java:234)
    at sun.net.www.http.HttpClient.New(HttpClient.java:307)
    at sun.net.www.http.HttpClient.New(HttpClient.java:324)
    at sun.net.www.protocol.http.HttpURLConnection.getNewHttpClient(HttpURLConnection.java:970)
    at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:911)
    at sun.net.www.protocol.http.HttpURLConnection.connect(HttpURLConnection.java:836)
    at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1172)
    at java.net.URL.openStream(URL.java:1010)
    at com.sun.xml.internal.ws.wsdl.parser.RuntimeWSDLParser.createReader(RuntimeWSDLParser.java:793)
    at com.sun.xml.internal.ws.wsdl.parser.RuntimeWSDLParser.resolveWSDL(RuntimeWSDLParser.java:251)
    at com.sun.xml.internal.ws.wsdl.parser.RuntimeWSDLParser.parse(RuntimeWSDLParser.java:118)
    ... 7 more
    
    
