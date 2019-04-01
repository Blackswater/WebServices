package org.dhbw.mosbach.ai.partyplanner.reducer;

import java.util.List;

public interface IReducer<T> {
/** itenterface to remove duplicates from the list */
    List<T> reduce(List<T> listToReduce);
}
