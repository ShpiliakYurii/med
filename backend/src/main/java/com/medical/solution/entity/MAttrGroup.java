package com.medical.solution.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medical.solution.serializers.LongToStringSerializer;
import com.medical.solution.serializers.StringToLongDeserializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MAttrGroup implements Serializable {

    @JsonSerialize(using = LongToStringSerializer.class)
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long attrGroupId;

    private String name;

    private String subgroup;

    private List<MAttribute> attributes;

    public MAttrGroup(Long attrGroupId, String name, String subgroup) {
        this.attrGroupId = attrGroupId;
        this.name = name;
        this.subgroup = subgroup;
        this.attributes = new ArrayList<>();
    }

    public MAttrGroup() {
        this.attributes = new ArrayList<>();
    }

    public Long getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(Long attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public List<MAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<MAttribute> attributes) {
        this.attributes = attributes;
    }
}
