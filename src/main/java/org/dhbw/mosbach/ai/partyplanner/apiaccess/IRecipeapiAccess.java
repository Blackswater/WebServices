package org.dhbw.mosbach.ai.partyplanner.apiaccess;

import org.dhbw.mosbach.ai.partyplanner.model.Item;

import java.util.List;
/**@author Pascal Röcker
 * Interface between REST and API access
 */
public interface IRecipeapiAccess{

    List<Item> searchapi(List<Item> items);
}
