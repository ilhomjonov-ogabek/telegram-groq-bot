package org.example.user;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UserDao2 {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public UserDao2(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  public void save(User user) {
    var sql = "insert into users(username, password,age) values (:username, :password, :age);";
    var params = Map.of(
        "username", user.getUsername(),
        "password", user.getPassword(),
        "age", user.getAge());
    namedParameterJdbcTemplate.update(sql, params);
  }

 /* public Integer save2(User user) {
    var sql = "insert into users(username, password,age) values (?, ?,?);";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update( con -> {
      var prst = con.prepareStatement(sql, new String[]{"id"});
      prst.setObject(1, user.getUsername());
      prst.setObject(2, user.getPassword());
      prst.setObject(3, user.getAge());
      return prst;
    }, keyHolder);
    Number key = keyHolder.getKey();
    return key.intValue();
  }

  public User findById(int id) throws SQLException {
    var sql = "select * from users where id = ?;";
    var rowMapper = BeanPropertyRowMapper.newInstance(User.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, id);
  }


  public List<User> findAll() throws SQLException {
    var sql = "select * from users;";
    var rowMapper = BeanPropertyRowMapper.newInstance(User.class);
    List<User> userList = jdbcTemplate.query(sql, rowMapper);
    return userList;
  }

  public void update(User user) throws SQLException {
    var sql = "update users set username = ?, password = ?,age=? where id = ?;";
    jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getAge(), user.getId());
  }



  public void deleteById( int id) throws SQLException {
    var sql = "delete from users where id = ?;";
    jdbcTemplate.update(sql, id);
  }*/

}
