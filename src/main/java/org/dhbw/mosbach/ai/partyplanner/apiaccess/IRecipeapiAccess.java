package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import org.dhbw.mosbach.ai.partyplanner.model.Item;

import java.util.List;

public interface IRecipeapiAccess{

    List<Item> searchapi(List<Item> items);
}
