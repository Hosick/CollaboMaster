package cm.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
