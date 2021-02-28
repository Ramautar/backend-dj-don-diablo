package nl.novi.backenddjdondiablo.service;

import nl.novi.backenddjdondiablo.domain.Demo;
import nl.novi.backenddjdondiablo.payload.request.DemoRequest;
import nl.novi.backenddjdondiablo.payload.response.DemoResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DemoService {

    long createDemo(Demo demo);
    ResponseEntity<DemoResponse> getDemosListByUser(DemoRequest demoRequest);
    ResponseEntity<DemoResponse> getAllDemos();
    void partialUpdateDemo(long id, String comment);

}
