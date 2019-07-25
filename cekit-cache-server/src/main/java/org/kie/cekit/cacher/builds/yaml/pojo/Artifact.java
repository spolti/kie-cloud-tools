package org.kie.cekit.cacher.builds.yaml.pojo;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Artifact {

    private String name;
    private String target;
    private String md5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "name='" + name + '\'' +
                ", target='" + target + '\'' +
                ", md5='" + md5 + '\'' +
                '}';
    }
}