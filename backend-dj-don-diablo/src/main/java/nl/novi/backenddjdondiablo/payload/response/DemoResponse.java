package nl.novi.backenddjdondiablo.payload.response;

import nl.novi.backenddjdondiablo.domain.Demo;

import java.util.Set;

public class DemoResponse {
    private Set<Demo> demo;

    public DemoResponse(Set<Demo> demo) {
        this.demo = demo;
    }

    public Set<Demo> getDemo() {
        return demo;
    }

    public void setDemo(Set<Demo> demo) {
        this.demo = demo;
    }
}
