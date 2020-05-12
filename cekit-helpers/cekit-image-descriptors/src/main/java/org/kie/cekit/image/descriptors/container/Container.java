package org.kie.cekit.image.descriptors.container;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "platforms",
        "compose"
})
@RegisterForReflection
public class Container {

    @JsonProperty("platforms")
    private Platforms platforms;
    @JsonProperty("compose")
    private Compose compose;

    public Container(){}

    @JsonProperty("platforms")
    public Platforms getPlatforms() {
        return platforms;
    }

    @JsonProperty("platforms")
    public void setPlatforms(Platforms platforms) {
        this.platforms = platforms;
    }

    @JsonProperty("compose")
    public Compose getCompose() {
        return compose;
    }

    @JsonProperty("compose")
    public void setCompose(Compose compose) {
        this.compose = compose;
    }

}