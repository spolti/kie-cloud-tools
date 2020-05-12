package org.kie.cekit.image.descriptors.module;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Execute {

    private String script;

    public Execute(){}

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public String toString() {
        return "Execute{" +
                "script='" + script + '\'' +
                '}';
    }
}