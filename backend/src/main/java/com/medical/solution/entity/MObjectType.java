package com.medical.solution.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medical.solution.serializers.LongToStringSerializer;
import com.medical.solution.serializers.StringToLongDeserializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MObjectType implements Serializable {

    @JsonSerialize(using = LongToStringSerializer.class)
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long objectTypeId;
    private String name;
    @JsonSerialize(using = LongToStringSerializer.class)
    @JsonDeserialize(using = StringToLongDeserializer.class)
    private Long parentId;

    private List<MObjectType> childes;

    public MObjectType() {
    }

    public MObjectType(Long objectTypeId, String name, Long parentId) {
        this.objectTypeId = objectTypeId;
        this.name = name;
        this.parentId = parentId;
        childes = new ArrayList<>();
    }

    public Long getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(Long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<MObjectType> getChildes() {
        return childes;
    }

    public void setChildes(List<MObjectType> childes) {
        this.childes = childes;
    }
}
