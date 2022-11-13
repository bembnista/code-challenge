package de.golde.developer.developer.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "developer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeveloperJpaEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true)
    public UUID id;

    @Column(name = "name", nullable = false)
    public String name;

}

