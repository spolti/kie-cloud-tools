package org.kie.cekit.cacher.builds.yaml.pojo;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@RegisterForReflection
public class Run {

    private Integer user;
    private List<String> cmd = null;

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public List<String> getCmd() {
        return cmd;
    }

    public void setCmd(List<String> cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "Run{" +
                "user=" + user +
                ", cmd=" + cmd +
                '}';
    }
}