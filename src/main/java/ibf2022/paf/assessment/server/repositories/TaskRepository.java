package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;

// TODO: Task 6

@Repository
public class TaskRepository {
    private static final String MYSQL_INSERT_NEW_TASK = """
        insert into task(description, priority, due_date, user_id)
        values
        (?,?,?,?)
            """;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertTask(Task task, User user){
        jdbcTemplate.update(MYSQL_INSERT_NEW_TASK, task.getDescription(), task.getPriority(), task.getDueDate(), user.getUserId());
    }
}
