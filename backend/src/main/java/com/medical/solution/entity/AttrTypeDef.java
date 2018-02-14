package com.medical.solution.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medical.solution.serializers.LongToStringSerializer;
import com.medical.solution.serializers.StringToLongDeserializer;

import java.io.Serializable;

public class AttrTypeDef implements Serializable{

    @JsonSerialize(using = LongToStringSerializer.class)
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long attrTypeDefId;

    private Integer attrTypeId;

    private String name;


    public AttrTypeDef() {}

    public AttrTypeDef(Long attrTypeDefId, Integer attrTypeId, String name) {
        this.attrTypeDefId = attrTypeDefId;
        this.attrTypeId = attrTypeId;
        this.name = name;
    }

    public Long getAttrTypeDefId() {
        return attrTypeDefId;
    }

    public void setAttrTypeDefId(Long attrTypeDefId) {
        this.attrTypeDefId = attrTypeDefId;
    }

    public Integer getAttrTypeId() {
        return attrTypeId;
    }

    public void setAttrTypeId(Integer attrTypeId) {
        this.attrTypeId = attrTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
