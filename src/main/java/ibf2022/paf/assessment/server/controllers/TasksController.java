package ibf2022.paf.assessment.server.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 4, Task 8

@Controller
public class TasksController {

    @PostMapping(path = "/", consumes = MediaType.TEXT_HTML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Task>> saveTasks(){
        return null;
    }
    
}
