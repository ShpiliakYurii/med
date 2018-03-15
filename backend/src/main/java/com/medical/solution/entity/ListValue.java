package com.medical.solution.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medical.solution.serializers.LongToStringSerializer;
import com.medical.solution.serializers.StringToLongDeserializer;

import java.io.Serializable;

public class ListValue implements Serializable {

    @JsonSerialize(using = LongToStringSerializer.class)
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long listValueId;

    @JsonSerialize(using = LongToStringSerializer.class)
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long attrTypeDefId;

    private String value;

    public ListValue() {
    }

    public ListValue(Long listValueId, Long attrTypeDefId, String value) {
        this.listValueId = listValueId;
        this.attrTypeDefId = attrTypeDefId;
        this.value = value;
    }

    public Long getListValueId() {
        return listValueId;
    }

    public void setListValueId(Long listValueId) {
        this.listValueId = listValueId;
    }

    public Long getAttrTypeDefId() {
        return attrTypeDefId;
    }

    public void setAttrTypeDefId(Long attrTypeDefId) {
        this.attrTypeDefId = attrTypeDefId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
