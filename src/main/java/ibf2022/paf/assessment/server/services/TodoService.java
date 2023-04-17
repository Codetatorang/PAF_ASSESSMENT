package ibf2022.paf.assessment.server.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {
    @Autowired
    TaskRepository taskRepo;
    @Autowired
    UserRepository userRepo;

    @Transactional(rollbackFor = UserException.class)
    public Boolean upsertTask(List<Task> taskList, String userName) throws UserException {
        Logger logger = LoggerFactory.getLogger(TodoService.class);

        User update_User = new User();
        try {
            if (userRepo.findUserByUsername(userName).isEmpty()) {
                // create a new user if not exists
                User new_user = new User();
                new_user.setName(userName.substring(0, 1).toUpperCase().substring(1));
                new_user.setUsername(userName);

                // generate uuid and add user to database
                String uuid = userRepo.insertUser(new_user);
                new_user.setUserId(uuid);

                update_User = new_user;
            } else {
                update_User = userRepo.findUserByUsername(userName).get();
            }

            // update tasks into database

            logger.info("user's user ID: %s".formatted(update_User.getUserId()));
            for (Task task : taskList) {
                taskRepo.insertTask(task, update_User);
            }
        } catch (Exception e) {
            throw new UserException("Reached error while inserting: " + e.getMessage());
        }
        return true;
    }
}
