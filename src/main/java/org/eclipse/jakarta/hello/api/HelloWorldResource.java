package org.eclipse.jakarta.hello.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.jakarta.hello.model.Product;
import org.eclipse.jakarta.hello.service.ProductService;

import java.util.List;

@Path("hello")
public class HelloWorldResource {


	@Inject
	private ProductService productService;

	//listamos todos los productos
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts() {
		List<Product> lista = productService.findAll();
		return Response.ok(lista).build();
	}
//listamos un producto mediante su id, si no existe el id en nuestra lista debolvemos un mensaje
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByID(@PathParam("id") int id) {
		Product product = productService.findById(id);
		if (product != null) {
			return Response.ok(product).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encuentra el producto con id: " + id)
					.build();
		}
	}

	//actualizamos un producto
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(@PathParam("id")int id,Product product) {
		//buscamos el producto

		if (productService.findById(id)!=null) {
			product.setId(id);
			Product p =productService.update(product);
			return Response.ok(p).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encuentra el producto con id: " + id)
					.build();
		}

	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(Product product) {
		if (productService.findById(product.getId()) == null) {
			Product p=productService.save(product);
			return Response.ok().status(Response.Status.CREATED).entity(p).build();

		}else{
			return Response.status(Response.Status.CONFLICT)
					.entity("Ya existe un producto con el id: " + product.getId())
					.build();
		}
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("id")int id) {
		//buscar si existe ese producto
		if(productService.findById(id)!=null){
			productService.delete(id);
			return Response.ok().status(Response.Status.ACCEPTED).entity("Se eliminó el producto con el id: "+id).build();

		}else{
			return Response.status(Response.Status.NOT_FOUND)
					.entity("No se encuentra el producto con id: " + id)
					.build();
		}
	}




}