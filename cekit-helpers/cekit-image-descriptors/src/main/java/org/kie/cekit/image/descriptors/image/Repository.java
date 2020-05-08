package org.kie.cekit.image.descriptors.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "git",
        "path"
})
@RegisterForReflection
public class Repository {

    @JsonProperty("name")
    private String name;
    @JsonProperty("git")
    private Git git;
    @JsonProperty("git")
    private String path;

    public Repository(){}

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("git")
    public Git getGit() {
        return git;
    }

    @JsonProperty("git")
    public void setGit(Git git) {
        this.git = git;
    }

    @JsonProperty("path")
    public String getPath() {
        return name;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

}
