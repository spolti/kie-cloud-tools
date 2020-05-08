package org.kie.cekit.image.descriptors.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "container_file"
})
@RegisterForReflection
public class Configuration {

    @JsonProperty("container_file")
    private String containerFile;

    public Configuration(){}

    @JsonProperty("container_file")
    public String getContainerFile() {
        return containerFile;
    }

    @JsonProperty("container_file")
    public void setContainerFile(String containerFile) {
        this.containerFile = containerFile;
    }

}