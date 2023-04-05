package ibf2022.paf.assessment.server.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoService;
import ibf2022.paf.assessment.server.services.UserException;

// TODO: Task 4, Task 8

@Controller
public class TasksController {

    Logger logger = LoggerFactory.getLogger(TasksController.class);

    @Autowired
    TodoService todoSvc;

    @PostMapping(path = "/task", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ModelAndView> saveTasks(@RequestBody MultiValueMap<String, String> payload) throws ParseException, UserException{
        logger.info("loaded into controller");
        List<Task> tasksList = new ArrayList<Task>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ModelAndView mav = new ModelAndView();

        System.out.println(payload.getFirst("username"));
        int counter = 0;
        while(true){
            String descriptionKey = "description-" + counter;
            String priorityKey = "priority-" + counter;
            String dueDateKey = "dueDate-" + counter;

            if(null== payload.getFirst(descriptionKey))
                break;
            
            //logic
            Task task = new Task();
            task.setDescription(payload.getFirst(descriptionKey));
            task.setPriority(Integer.parseInt( payload.getFirst(priorityKey)));
            task.setDueDate(dateFormat.parse( payload.getFirst(dueDateKey)));
            task.setUser_id(payload.getFirst("username"));
            
            tasksList.add(task);

            //increment the counter
            counter++;

            logger.info("iteration count" + counter);
        }

        String userName = payload.getFirst("username");
        logger.info(">>>username value: " + userName);
        //execute the the saving of task
        try {
            todoSvc.upsertTask(tasksList, userName);
        } catch (UserException e) {
            // throw new UserException("Error when saving %s's task: ".formatted(userName) + e.getMessage());
            mav.setViewName("error");

            return new ResponseEntity<ModelAndView>(mav,HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            mav.addObject("taskCount", counter);
            mav.addObject("username", userName);
            mav.setViewName("result");
        }
        
        return new ResponseEntity<ModelAndView>(mav,HttpStatus.OK);
    }
    

}
