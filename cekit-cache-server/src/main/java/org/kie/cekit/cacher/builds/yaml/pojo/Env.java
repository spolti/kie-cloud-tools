package org.kie.cekit.cacher.builds.yaml.pojo;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Env {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Env{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}