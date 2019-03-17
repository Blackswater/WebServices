package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import java.util.List;


public interface IIngredientsService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    List<JSONObject> getIngredients(List<Item> list);


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    List<JSONObject> getIngredients(List<String> drinks,List<String> food);
}
