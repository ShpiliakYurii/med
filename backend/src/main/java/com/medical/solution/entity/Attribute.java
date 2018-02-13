package com.medical.solution.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medical.solution.serializers.LongToStringSerializer;
import com.medical.solution.serializers.StringToLongDeserializer;

import java.io.Serializable;

public class Attribute implements Serializable {

    @JsonSerialize(using = LongToStringSerializer.class)
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long attrId;

    private String name;

    @JsonSerialize(using = LongToStringSerializer.class)
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long attrGroupId;

    private Integer attrTypeId;

    public Attribute() {
    }

    public Attribute(Long attrId, String name, Long attrGroupId, Integer attrTypeId) {
        this.attrId = attrId;
        this.name = name;
        this.attrGroupId = attrGroupId;
        this.attrTypeId = attrTypeId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(Long attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    public Integer getAttrTypeId() {
        return attrTypeId;
    }

    public void setAttrTypeId(Integer attrTypeId) {
        this.attrTypeId = attrTypeId;
    }
}
