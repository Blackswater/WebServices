package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;
import org.dhbw.mosbach.ai.partyplanner.model.Item;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


public interface IIngredientsService {

    @POST
    @Path("/items")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Item> getItems(List<Item> list);


    @POST
    @Path("/itemsList")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Item> getItems(DrinkFoodPOJO pojo);

    @POST
    @Path("/ingredientsList")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Ingredient> getIngredients(DrinkFoodPOJO pojo);

    @POST
    @Path("/ingredients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Ingredient> getIngredients(List<Item> list);

}
