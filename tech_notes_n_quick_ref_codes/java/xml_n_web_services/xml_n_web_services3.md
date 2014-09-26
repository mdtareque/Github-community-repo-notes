Product.java
    package entity;
    import javax.xml.bind.annotation.XmlRootElement;
    @XmlRootElement // JAXB
    //(name="prd")
    public class Product {
    private int id;
    private String name;
    private double price;
    public Product(int i, String string, int j) {
        this.id = i;
        this.name = string;
        this.price = j;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    }

ProductDao.java
    package dao;
    import java.util.ArrayList;
    import java.util.List;
    import javax.enterprise.context.ApplicationScoped;
    import entity.Product;
    @ApplicationScoped
    // CDI Context Time Dependency Injection (Alternative to spring DI)
    // Grizzly (glassfish) , Weld for DI
    public class ProductDao {
    private List<Product> products;
    public ProductDao() {
        products = new ArrayList<Product>();
        products.add(new Product(123, "Nokia 234", 3456));
        products.add(new Product(123, "Nokia 234", 3456));
    }
    public void add(Product p) {
        products.add(p);
    }
    public List<Product> getAll() {
        return products;
    }
    public Product get(int pid) {
        for(Product p: products) {
            if(p.getId() == pid) {
                return p;
            }
        }
        return null;
     }
     
    }
    
 Click here to expand...

    package application;
    /* Write this empty file or map javax.ws.rs.core.Application as a servlet in web.xml */
    import javax.ws.rs.ApplicationPath;
    import javax.ws.rs.core.Application;
    @ApplicationPath("/rest")
    public class MyRESTfulApplication extends Application {
    }

ProductResource.java

    package resource;
    import java.util.List;
    import javax.inject.Inject;
    import javax.ws.rs.GET;
    import javax.ws.rs.Path;
    import javax.ws.rs.PathParam;
    import dao.ProductDao;
    import entity.Product;
    @Path("/product")
    public class ProductResource {
     
    @Inject
    private ProductDao productDao;
    @GET
    public List<Product> getAll() {
        return productDao.getAll();
    }
     
    @GET
    @Path("/{productId}")
    public Product getProduct(@PathParam("productId") int pid) {
        return productDao.get(pid);
    }
    }

Create empty beans.xml file in WEB-INF/ (required by jersey or glassfish as CDI is used)

Check wadl http://localhost:8080/RSApp/rest/application.wadl




 ProductResource modified with POST tested

    package resource;
    import java.net.URI;
    import java.util.ArrayList;
    import java.util.List;
    import javax.inject.Inject;
    import javax.ws.rs.Consumes;
    import javax.ws.rs.GET;
    import javax.ws.rs.POST;
    import javax.ws.rs.Path;
    import javax.ws.rs.PathParam;
    import javax.ws.rs.Produces;
    import javax.ws.rs.WebApplicationException;
    import javax.ws.rs.core.Context;
    import javax.ws.rs.core.MediaType;
    import javax.ws.rs.core.Response;
    import javax.ws.rs.core.UriBuilder;
    import javax.ws.rs.core.UriInfo;
    import dao.ProductDao;
    import entity.Product;
    @Path("/product")
    public class ProductResource {
        @Inject
        private ProductDao productDao;
    @Context
    private UriInfo uriInfo;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/uri")
    public Response getUri() {
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        URI uri = uriBuilder.path(ProductResource.class, "getUri").build(new Integer(123));
        System.out.println("sending uri");
        return Response.created(uri).build(); // http status code 201 (created)
    }
    @GET
    // cool !!!!
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Product> getAll() {
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product(123, "Nokia 234", 3456));
        products.add(new Product(124, "Samsung 234", 32456));
        // return productDao.getAll();
        return products;
    }
    @GET
    @Path("/{productId}")
    // @QueryParam can be used in method signature, then the url should be
    // product?<productId>
    // @FormParam coming in form in post
    public Product getProduct(@PathParam("productId") int pid) {
        if (pid == 123) {
            return new Product(123, "Nokia 234", 3456);
        } else if (pid == 124) {
            return new Product(124, "Samsung 234", 32456);
        } else {
            if (pid == 5) {
                System.out.println("throwing WebApplicationException");
                throw new WebApplicationException(404);
            }
            return new Product();
        }
    }
    // Overriden
    // can't overriden with same number of argument but different datatype,
    // different return types also doesn't work
    /*
     * @GET
     *
     * @Path("/{productId}") public String getProduct(@PathParam("productId")
     * String s) { System.out.println("here 1st overide " + s ); return
     * "Output"; }
     */
    @GET
    @Path("/{productId}/{catid}")
    public Product getProduct(@PathParam("productId") String s,
            @PathParam("catid") int id) {
        System.out.println("here " + s + " " + id);
        return new Product(124, "Samsung 234", 32456);
    }
    // Tested by sending post json request from soap, product was created, http reponse was verified
    // sent location url of new product
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        // add to productDao
        System.out.println("Adding product");
        // return Response.ok().build(); // http status code 200 (success)
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        URI uri = uriBuilder.path(ProductResource.class, "getProduct").build(
                product.getId());
        return Response.created(uri).build(); // http status code 201 (created)
    }
    }

Client code, created simple servlet in same server project (as jars present in framework only and not in core java as in case of ws)


RS client servlet

    package client;
 
    import java.io.IOException;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
 
    import com.sun.jersey.api.client.Client;
 
    /**
         * Servlet implementation class ProductServlet
     */
    @WebServlet("/ProductServlet")
    public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    public ProductServlet() {
        super();
    }
    /* below will work in glassfish4 and jdk7 jars
     * classical way would be to open a URL connection and write code to extract data by json parsing or xml jaxb processing
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target("http://locahost:8080/RSApp/rest/product")
                .path("{productID}")
                .resolveTemplate("productId" , 123);
        Product product = resource.request(MediaType.APPLICATION_JSON).get(Product.class);
        response.getWriter().print(product.getName());
 
        // add a new Product
        Product prd = new Product();
        prd.setId(456);
        prd.setName("Moto G");
        prd.setPrice(20000);
 
        Client client = ClientBuilder.newClient();
        resource = client.target("http://locahost:8080/RSApp/rest/product")
        resource.request(MediaType.APPLICATION_JSON).post(Entity.json(prd));
         
         
        client.close();
    }
    */
    }



