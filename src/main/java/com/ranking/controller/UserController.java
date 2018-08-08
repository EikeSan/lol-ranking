package com.ranking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.ranking.model.User;;
 
@RestController
public class UserController {
 
  private Map<Integer, User> users;
 
  public UserController() {
    users = new HashMap<Integer, User>();
 
    User c1 = new User(1, "João");
    User c2 = new User(2, "Maria");
    User c3 = new User(3, "Julia");
 
    users.put(1, c1);
    users.put(2, c2);
    users.put(3, c3);
  }
 
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public ResponseEntity<List<User>> listar() {
    return new ResponseEntity<List<User>>(new ArrayList<User>(users.values()), HttpStatus.OK);
  }
 
}