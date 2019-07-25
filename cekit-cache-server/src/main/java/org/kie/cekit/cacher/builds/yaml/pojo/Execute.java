package org.kie.cekit.cacher.builds.yaml.pojo;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Execute {


    private String script;

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