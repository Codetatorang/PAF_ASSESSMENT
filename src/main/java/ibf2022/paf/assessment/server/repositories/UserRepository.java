package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;

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
        User user = jdbcTemplate.queryForObject(MYSQL_FIND_BY_USERNAME, BeanPropertyRowMapper.newInstance(User.class), username);

        if (null == user){
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public String insertUser(User user){
        String user_id = UUID.randomUUID().toString().substring(0,8);

        Integer iUpdated = jdbcTemplate.update(MYSQL_INSERT_NEW_USER, user_id, user.getUsername(), user.getName());

        if(iUpdated <= 0){
            //return execption here
            return null;
        }
        return user_id;
        
    }
}
