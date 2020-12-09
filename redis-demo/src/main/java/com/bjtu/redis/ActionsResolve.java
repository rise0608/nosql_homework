package com.bjtu.redis;

import java.util.List;

public class ActionsResolve {
    public void executeAction(ActionsSpec actionsSpec) throws Exception {

        List<String> featureRetrieveList = actionsSpec.getFeatureRetrieve();
        List<String> saveCounterList = actionsSpec.getSaveCounter();

        String featureRetrieve = null;
        String saveCounter = null;

        for (String s : featureRetrieveList) {
            featureRetrieve = s;
        }
        for (String s : saveCounterList) {
            saveCounter = s;
        }

        CountersResolve countersResolve = new CountersResolve();
        countersResolve.executeCounter(saveCounter);
        System.out.println();
        countersResolve.executeCounter(featureRetrieve);
        System.out.println();
    }
}
