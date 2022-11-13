package de.golde.developer.repository.persistence;

import de.golde.developer.developer.persistence.DeveloperJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "repository")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RepositoryJpaEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true)
    public UUID id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "url", nullable = false)
    public String url;

    @Column(name = "language")
    public String language;

    @Column(name = "developer_id", nullable = false)
    public UUID developer_id;
}
