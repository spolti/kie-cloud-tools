package org.kie.cekit.image.descriptors;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "x86_64"
})
@RegisterForReflection
public class ContentSets {

    @JsonProperty("x86_64")
    private List<String> x8664;

    public ContentSets(){}

    @JsonProperty("x86_64")
    public List<String> getX8664() {
        return x8664;
    }

    @JsonProperty("x86_64")
    public void setX8664(List<String> x8664) {
        this.x8664 = x8664;
    }
}

