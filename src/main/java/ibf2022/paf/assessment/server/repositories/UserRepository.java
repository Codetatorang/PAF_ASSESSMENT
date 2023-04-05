package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.services.UserException;

// TODO: Task 3

@Repository
public class UserRepository {
    private static final String MYSQL_FIND_BY_USERNAME = "select * from user where username = ?";

    private static final String MYSQL_INSERT_NEW_USER = """
        insert into user(user_id, username, name)
        values
        (?,?,?);
            """;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> findUserByUsername(String username){
        try {
            User user = jdbcTemplate.queryForObject(MYSQL_FIND_BY_USERNAME, BeanPropertyRowMapper.newInstance(User.class), username);

            return Optional.of(user);
        } catch (Exception ex) {
            return Optional.empty();
        }

    }

    public String insertUser(User user)throws UserException{
        String user_id = UUID.randomUUID().toString().substring(0,8);

        Integer iUpdated = jdbcTemplate.update(MYSQL_INSERT_NEW_USER, user_id, user.getUsername(), user.getName());

        if(iUpdated <= 0){
            throw new UserException("Something went wrong, cannot update user");
        }
        return user_id;
        
    }
}
