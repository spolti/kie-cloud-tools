package org.kie.cekit.image.descriptors.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "configuration",
        "extra_dir",
        "repository"
})
@RegisterForReflection
public class Osbs {

    @JsonProperty("configuration")
    private Configuration configuration;
    @JsonProperty("extra_dir")
    private String extraDir;
    @JsonProperty("repository")
    private RepositoryOsbs repository;

    public Osbs(){}

    @JsonProperty("configuration")
    public Configuration getConfiguration() {
        return configuration;
    }

    @JsonProperty("configuration")
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @JsonProperty("extra_dir")
    public String getExtraDir() {
        return extraDir;
    }

    @JsonProperty("extra_dir")
    public void setExtraDir(String extraDir) {
        this.extraDir = extraDir;
    }

    @JsonProperty("repository")
    public RepositoryOsbs getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(RepositoryOsbs repository) {
        this.repository = repository;
    }

}
