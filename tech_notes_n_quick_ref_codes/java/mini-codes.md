change value of string by reflection

    public static void changeVariable(String s1, String s2) {
      try {
        Field f = String.class.getDeclaredField("value");
        f.setAccessible(true);
        f.set(s1, s2.toCharArray());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  
xpath test
  
    import java.io.ByteArrayInputStream;
    import java.io.StringReader;
    import java.io.StringWriter;
     
    import javax.xml.soap.MessageFactory;
    import javax.xml.soap.SOAPBody;
    import javax.xml.soap.SOAPMessage;
    import javax.xml.soap.SOAPPart;
    import javax.xml.transform.Transformer;
    import javax.xml.transform.TransformerFactory;
    import javax.xml.transform.dom.DOMSource;
    import javax.xml.transform.stream.StreamResult;
    import javax.xml.transform.stream.StreamSource;
    import javax.xml.xpath.XPath;
    import javax.xml.xpath.XPathConstants;
    import javax.xml.xpath.XPathExpressionException;
    import javax.xml.xpath.XPathFactory;
     
    import org.w3c.dom.Node;
    import org.xml.sax.InputSource;
     
    public class XpathTest {
         
        /*
        malformed xml as input then below exception
         
        [Fatal Error] :1:39: Content is not allowed in prolog.
    org.xml.sax.SAXParseException: Content is not allowed in prolog.
        at com.sun.org.apache.xerces.internal.parsers.DOMParser.parse(DOMParser.java:239)
        at com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderImpl.parse(DocumentBuilderImpl.java:283)
        at com.sun.org.apache.xpath.internal.jaxp.XPathImpl.evaluate(XPathImpl.java:468)
        at toPrint.XpathTest.getNodeText(XpathTest.java:25)
         
         
        */
        public static void main(String[] args) throws Exception {
            String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns3:abcxmlns:ns2=\"http://abc.com/\" xmlns:ns4=\"http://abc.com/response\" xmlns:ns3=\"http://abc.com/s\"><ns4:Response><ID>dg43wer</ID><Status>SUCCESS</Status></ns4:Response></ns3:abc></soapenv:Body></soapenv:Envelope>";
            System.out.println(getNodeText(response, "//Status"));
            System.out.println(getNodeText(response, "//ID"));
             
            System.out.println(stripSoapWrapper(response));
        }
     
        private static String getNodeText(String response, String path) {
            Node node = null;
            try {
                InputSource source = new InputSource(new StringReader(response));
                XPath xPath = XPathFactory.newInstance().newXPath();
                node = (Node) xPath.evaluate(path, source, XPathConstants.NODE);
                if (node == null) {
                    return "";
                }
            } catch (XPathExpressionException ee) {
                ee.printStackTrace();
            }
     
            return node.getTextContent();
        }
     
        // Strip message of soap tags to get access to message
        public static String stripSoapWrapper(String soapText)throws Exception {
                String  out = null;
                MessageFactory msgFactory     = MessageFactory.newInstance();
                SOAPMessage message           = msgFactory.createMessage();
                SOAPPart soapPart             = message.getSOAPPart();
     
                // Load the SOAP text into a stream source
                byte[] buffer                 = soapText.getBytes();
                ByteArrayInputStream stream   = new ByteArrayInputStream(buffer);
                StreamSource source           = new StreamSource(stream);
                // Set contents of message
                soapPart.setContent(source);
     
                //Try accessing the SOAPBody
                SOAPBody soapBody = message.getSOAPBody();
                Node tradeInquiryNode = soapBody.getFirstChild();
                StringWriter sw = new StringWriter();
                Transformer serializer = TransformerFactory.newInstance().newTransformer();
                serializer.transform(new DOMSource(tradeInquiryNode), new StreamResult(sw));
                out = sw.toString();
                // Log this out
    //            System.out.println(out);
                return out;
        }
    }
    
    
load-ssl-cert
http://stackoverflow.com/questions/6994944/connect-to-a-https-site-with-a-given-p12-certificate
    
    HttpsURLConnection.setDefaultSSLSocketFactory(loadCertificate(cert, certPwd).getSocketFactory());
    HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
     
    connection.setDoOutput(true);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "text/xml");
    connection.setRequestProperty("Content-Transfer-Encoding", "8bit");
    OutputStream out = connection.getOutputStream();
    out.write(msg.getBytes());
    out.flush();
    out.close();
    connection.connect();
    int responseCode = connection.getResponseCode();
     
    loadCertificate Method
    InputStream certFile = new FileInputStream(certfilepath);
    KeyStore ks = KeyStore.getInstance("PKCS12");
    char[] certPwd = password.toCharArray();
    ks.load(certFile, certPwd);
    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    kmf.init(ks, certPwd);
    SSLContext sslContext = SSLContext.getInstance("SSL");
    TrustManager[] trustMan = { new DummyTrustManager() };
    sslContext.init(kmf.getKeyManagers(), trustMan, null);
    return sslContext;
     
    class DummyTrustManager
    implements X509TrustManager
    {
        public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
            throws CertificateException
            {}
        public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
            throws CertificateException
            {}
        public X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }
    }
    
compareDouble

   double EPSILON = 0.000001;
   public boolean compareDouble(double a, double b){
          return a == b || Math.abs(a - b) < EPSILON;
   }
   
hasNonNullIntersection

    public static <K> boolean hasNonNullIntersection( Set<K> master, Set<K> child ) {
    final int mSize = master == null ? 0 : master.size();
    final int cSize = child == null ? 0 : child.size();
    if ( cSize != 0 && mSize != 0 )
    {
            final Set<K> smallContainer = mSize < cSize ? master : child;
            final Set<K> largeContainer = ( smallContainer == master ) ? child : master;
            for ( K pk : smallContainer )
            {
                    if ( largeContainer.contains( pk ) )
                            return true;
            }
    }  
    return false;
    }
    

Check jvm params default value

    java -XX:+PrintFlagsFinal -version 
    java -XshowSettings:all    /* java7 */

Check runtime params programmatically

    import java.lang.management.ManagementFactory;
    import java.lang.management.RuntimeMXBean;
    import java.util.List;
    ...
    public static void runtimeParameters() {
    	RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
		List<String> aList = bean.getInputArguments();
		
		for (int i = 0; i < aList.size(); i++) {
			System.out.println( aList.get( i ) );
		}
	}
