package org.kie.cekit.image.descriptors.container;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pulp_repos"
})
@RegisterForReflection
public class Compose {

    @JsonProperty("pulp_repos")
    private Boolean pulpRepos;

    public Compose(){}

    @JsonProperty("pulp_repos")
    public Boolean getPulpRepos() {
        return pulpRepos;
    }

    @JsonProperty("pulp_repos")
    public void setPulpRepos(Boolean pulpRepos) {
        this.pulpRepos = pulpRepos;
    }

}
