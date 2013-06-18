package raz.test.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * <a href="http://docs.oracle.com/javaee/6/tutorial/doc/gijqy.html">what are
 * restful web services</a>
 * <p>
 * RESTful web services are built to work best on the Web. Representational
 * State Transfer (REST) is an architectural style, that specifies constraints
 * such as the uniform interface, that if applied to a web service induces
 * desirable properties, such as
 * <ul>
 * <li>performance</li>
 * <li>scalability</li>
 * <li>modifiability</li>
 * </ul>
 * that enable services to work best on the Web
 * </p>
 * <p>
 * In the REST architectural style, data and functionality are considered
 * resources and are accessed <b>using Uniform Resource Identifiers (URIs)</b>,
 * typically links on the Web.
 * </p>
 * 
 * <p>
 * The resources(data and functionality - which are considered URIs/links) are
 * acted upon by using a set of simple, well-defined operations. The REST
 * architectural style constrains an architecture to a client/server
 * architecture and is designed to use a stateless communication protocol,
 * typically HTTP. In the REST architecture style, clients and servers exchange
 * representations of resources by using a standardized interface and protocol.
 * </p>
 * 
 * <p>
 * The following principles encourage RESTful applications to be simple,
 * lightweight, and fast:
 * <p>
 * <b>Resource identification through URI:</b> A RESTful web service exposes a
 * set of resources that identify the targets of the interaction with its
 * clients. Resources are identified by URIs, which provide a global addressing
 * space for resource and service discovery. See The @Path Annotation and URI
 * Path Templates for more information.
 * </p>
 * <p>
 * <b>Uniform interface:</b> Resources are manipulated using a fixed set of four
 * operations - create, read, update, delete: PUT, GET, POST, and DELETE. PUT
 * creates a new resource, which can be then deleted by using DELETE. GET
 * retrieves the current state of a resource in some representation. POST
 * transfers a new state onto a resource. See Responding to HTTP Methods and
 * Requests for more information.
 * </p>
 * <p>
 * <b>Self-descriptive messages:</b> Resources are decoupled from their
 * representation so that their content can be accessed in a variety of formats,
 * such as HTML, XML, plain text, PDF, JPEG, JSON, and others. Metadata about
 * the resource is available and used, for example, to control caching, detect
 * transmission errors, negotiate the appropriate representation format, and
 * perform authentication or access control.
 * </p>
 * <p>
 * <b>Stateful interactions through hyperlinks:</b> Every interaction with a
 * resource is stateless; that is, request messages are self-contained. Stateful
 * interactions are based on the concept of explicit state transfer. Several
 * techniques exist to exchange state, such as URI rewriting, cookies, and
 * hidden form fields. State can be embedded in response messages to point to
 * valid future states of the interaction. See Using Entity Providers to Map
 * HTTP Response and Request Entity Bodies and “Building URIs” in the JAX-RS
 * Overview document for more information.
 * </p>
 * <p>
 * --------
 * </p>
 * <p>
 * <h3>Creating a RESTful Root Resource Class</h3>
 * <p>
 * <b>Root resource classes</b> are POJOs that are either annotated with @Path
 * or have at least one method annotated with @Path or a <b>request method
 * designator</b>, such as @GET, @PUT, @POST, or @DELETE.
 * </p>
 * <p>
 * <b>Resource methods</b> are methods of a resource class annotated with a
 * request method designator.
 * </p>
 * </p>
 * */
// --------------------------
// 1. MyRestService - its a java POJO
// -------
/*
 * - The @Path annotation’s value is a relative URI path - the Java class will
 * be hosted at the URI path <code> /helloworld.</code>
 */
/*
 * The @Path annotation identifies the URI path template to which the resource
 * responds and is specified at the class or method level of a resoirce. The
 * 
 * @Path annotation's value is a partial URI path template relative to 1)the
 * base URI of the server on which the resource is deployed, 2)the context root
 * of the application, 3)the URL pattern to which the JAX-RS runtime responds
 */
@Path("/helloworld")
public class MyRestService {

	// The Java method will process HTTP GET requests
	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces("text/plain")
	public String getClichedMessage() {
		return "Hello World!!";
	}

	/**
	 * <p>
	 * Responding to HTTP Methods and Requests
	 * </p>
	 * <p>
	 * The behavior of a resource is determined by the HTTP methods (typically,
	 * GET, POST, PUT, DELETE) to which the resource is responding.
	 * </p>
	 * <p>
	 * The Request Method Designator Annotations
	 * </p>
	 * <p>
	 * Request method designator annotations are runtime annotations, defined by
	 * JAX-RS, that correspond to the similarly named HTTP methods. Within a
	 * resource class file, HTTP methods are mapped to Java programming language
	 * methods by using the request method designator annotations. The behavior
	 * of a resource is determined by which HTTP method the resource is
	 * responding to. JAX-RS defines a set of request method designators for the
	 * common HTTP methods @GET, @POST, @PUT, @DELETE, and @HEAD; you can also
	 * create your own custom request method designators.
	 * </p>
	 * <p>
	 * Methods decorated with request method designators must return void, a
	 * Java programming language type, or a
	 * <code>javax.ws.rs.core.Response</code> object.
	 * </p>
	 * <p>
	 * Multiple parameters may be extracted from the URI by using the @PathParam
	 * or @QueryParam annotations as described in Extracting Request Parameters.
	 * </p>
	 * <p>
	 * Conversion between Java types and an entity body is the responsibility of
	 * an entity provider, such as MessageBodyReader or MessageBodyWriter.
	 * </p>
	 * <p>
	 * Methods that need to provide additional metadata with a response should
	 * return an instance of the @javax.ws.rs.core.Response class. The
	 * ResponseBuilder class provides a convenient way to create a Response
	 * instance using a builder pattern.
	 * </p>
	 * <p>
	 * The HTTP PUT and POST methods expect an HTTP request body, so you should
	 * use a MessageBodyReader for methods that respond to PUT and POST
	 * requests.
	 * </p>
	 * <p>
	 * Both @PUT and @POST can be used to create or update a resource. POST can
	 * mean anything, so when using POST, it is up to the application to define
	 * the semantics. PUT has well-defined semantics. When using PUT for
	 * creation, the client declares the URI for the newly created resource.
	 * </p>
	 * <p>
	 * PUT has very clear semantics for creating and updating a resource. The
	 * representation the client sends must be the same representation that is
	 * received using a GET, given the same media type. PUT does not allow a
	 * resource to be partially updated, a common mistake when attempting to use
	 * the PUT method. A common application pattern is to use POST to create a
	 * resource and return a 201 response with a location header whose value is
	 * the URI to the newly created resource. In this pattern, the web service
	 * declares the URI for the newly created resource.
	 * </p>
	 * */

	/**
	 * <b>Using Entity Providers to Map HTTP Response and Request Entity
	 * Bodies</b>
	 * <p>
	 * Entity providers supply mapping services between representations and
	 * their associated Java types.
	 * </p>
	 * <p>
	 * For HTTP requests, the @MessageBodyReader is used to map an HTTP request
	 * entity body to method parameters.
	 * </p>
	 * <p>
	 * On the response side, a return value is mapped to an HTTP response entity
	 * body by using a @MessageBodyWriter.
	 * </p>
	 * <p>
	 * If the application needs to supply additional metadata, such as HTTP
	 * headers or a different status code, a method can return a Response that
	 * wraps the entity and that can be built by using Response.ResponseBuilder.
	 * </p>
	 * */

	/**
	 * <b>Using @Consumes and @Produces to Customize Requests and Responses</b>
	 * <p>
	 * The information sent to a resource and then passed back to the client is
	 * specified as a MIME media type in the headers of an HTTP request or
	 * response. You can specify which MIME media types of representations a
	 * resource can respond to or produce by using the following annotations: @Consumes
	 * and @Produces
	 * </p>
	 * */

}
