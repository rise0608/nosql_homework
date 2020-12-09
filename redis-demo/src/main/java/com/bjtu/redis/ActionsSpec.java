package com.bjtu.redis;

import java.util.List;

public class ActionsSpec {

    private enum ActionType {
        INCR_USER,
        DECR_USER,
        INCR_USER_FREQ,
        DECR_USER_FREQ,
        ADD_STRING,
        ADD_LIST
    }

    private ActionType type;
    private List<String> featureRetrieve;
    private List<String> saveCounter;

    public ActionsSpec() {

    }

    public String getActionType() {
        return type.name();
    }

    public void setActionType(String type) {
        switch (type) {
            case "INCR_USER":
                this.type = ActionType.INCR_USER;
                break;
            case "DECR_USER":
                this.type = ActionType.DECR_USER;
                break;
            case "INCR_USER_FREQ":
                this.type = ActionType.INCR_USER_FREQ;
                break;
            case "DECR_USER_FREQ":
                this.type = ActionType.DECR_USER_FREQ;
                break;
            case "ADD_STRING":
                this.type = ActionType.ADD_STRING;
                break;
            case "ADD_LIST":
                this.type = ActionType.ADD_LIST;
                break;
            default:
                System.out.println("Illegal action type!");
                break;
        }
    }

    public List<String> getFeatureRetrieve() {
        return featureRetrieve;
    }

    public void setFeatureRetrieve(List<String> featureRetrieve) {
        this.featureRetrieve = featureRetrieve;
    }

    public List<String> getSaveCounter() {
        return saveCounter;
    }

    public void setSaveCounter(List<String> saveCounter) {
        this.saveCounter = saveCounter;
    }

    @Override
    public String toString() {
        return "ActionSpec{" +
                "featureRetrieve=" + featureRetrieve +
                ", saveCounter=" + saveCounter +
                '}';
    }
}
