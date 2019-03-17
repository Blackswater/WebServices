package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.json.JSONObject;

import javax.ws.rs.Path;
import java.util.List;
@Path("/ingredients")
public class IngredientService implements IIngredientsService {
    @Override
    public List<JSONObject> getIngredients(List<Item> list) {
        return null;
    }

    @Override
    public List<JSONObject> getIngredients(List<String> drinks, List<String> food) {

        return null;
    }
}
