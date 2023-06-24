package com.example.taskssystem.entities;

//https://spring.io/guides/tutorials/rest/

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@Entity(name = "Task")
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;
    @Column(unique = true, nullable = false, updatable = false)
    private String name;
    @Column()
    private Integer priority;

    @Override
    public String toString() {
        return "Task{" + "id=" + this.id + ", name='" + this.name + '\'' + ", priority='" + this.priority + '\'' + '}';
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(id);
        hcb.append(name);
        hcb.append(priority);
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { //same reference
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Task that = (Task) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(id, that.id);
        eb.append(name, that.name);
        eb.append(priority, that.priority);
        return eb.isEquals();
    }
}
