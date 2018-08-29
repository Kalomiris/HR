package com.afse.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "mailbox")
public class EmailMassage implements Serializable {

    private static final long serialVersionUID = 5604621402060410259L;

    @Id
    @GeneratedValue(generator = "EMAILSEQ")
    @SequenceGenerator(name = "EMAILSEQ", sequenceName = "EMAILSEQ", allocationSize = 1)
    private Long id;

    @NotNull(message = "Email message is empty!")
    private String message;

    /**
     * Version for optimistic locking
     */
    @Version
    @Column(name = "dbversion", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
