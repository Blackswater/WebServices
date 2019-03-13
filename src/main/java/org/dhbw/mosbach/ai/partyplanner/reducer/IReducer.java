package org.dhbw.mosbach.ai.partyplanner.reducer;

import java.util.List;

public interface IReducer<T> {

    List<T> reduce(List<T> listToReduce);
}
