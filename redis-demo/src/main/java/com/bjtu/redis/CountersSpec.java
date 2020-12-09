package com.bjtu.redis;

public class CountersSpec {
    private String counterName;
    private String counterIndex;
    private String type;
    private String keyFields;
    private String valueFields;
    private String fields;

    public CountersSpec(String counterName, String counterIndex, String type, String keyFields, String fields, String valueFields) {
        this.counterName = counterName;
        this.counterIndex = counterIndex;
        this.type = type;
        this.keyFields = keyFields;
        this.fields = fields;
        this.valueFields = valueFields;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getCounterIndex() {
        return counterIndex;
    }

    public void setCounterIndex(String counterIndex) {
        this.counterIndex = counterIndex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyFields() {
        return keyFields;
    }

    public void setKeyFields(String keyFields) {
        this.keyFields = keyFields;
    }

    public String getValueFields() {
        return valueFields;
    }

    public void setValueFields(String valueFields) {
        this.valueFields = valueFields;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "CounterSpec{" +
                "counterName='" + counterName + '\'' +
                ", counterIndex='" + counterIndex + '\'' +
                ", type='" + type + '\'' +
                ", keyFields='" + keyFields + '\'' +
                ", fields='" + fields + '\'' +
                ", valueFields='" + valueFields +
                '}';
    }

}
