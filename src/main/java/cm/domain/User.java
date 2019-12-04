package cm.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id @GeneratedValue()
    String id;

    @Column(name = "username")
    String name;

    String password;
}
