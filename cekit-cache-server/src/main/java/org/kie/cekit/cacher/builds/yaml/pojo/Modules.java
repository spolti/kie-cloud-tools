package org.kie.cekit.cacher.builds.yaml.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@JsonPropertyOrder({
        "schemaVersion",
        "name",
        "description",
        "labels",
        "envs",
        "ports",
        "artifacts",
        "run",
        "execute"
})
@RegisterForReflection
public class Modules {

    @JsonProperty("schema_version")
    private Integer schemaVersion;
    private String name;
    private String description;
    private List<Label> labels = null;
    private List<Env> envs = null;
    private List<Port> ports = null;
    private List<Artifact> artifacts = null;
    private Run run;
    private List<Execute> execute = null;

    public Integer getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(Integer schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Env> getEnvs() {
        return envs;
    }

    public void setEnvs(List<Env> envs) {
        this.envs = envs;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public List<Execute> getExecute() {
        return execute;
    }

    public void setExecute(List<Execute> execute) {
        this.execute = execute;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "schemaVersion=" + schemaVersion +
                ", name='" + name +
                ", description='" + description +
                ", labels=" + labels.toString() +
                ", envs=" + envs.toString() +
                ", artifacts=" + artifacts.toString() +
                ", run=" + run.toString() +
                ", execute=" + execute.toString() +
                '}';
    }
}