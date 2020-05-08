package org.kie.cekit.image.descriptors.container;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "only"
})
@RegisterForReflection
public class Platforms {

    @JsonProperty("only")
    private List<String> only;

    public Platforms(){}

    @JsonProperty("only")
    public List<String> getOnly() {
        return only;
    }

    @JsonProperty("only")
    public void setOnly(List<String> only) {
        this.only = only;
    }

}