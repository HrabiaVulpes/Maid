package org.example.server.model;

import javax.persistence.*;

@Entity
@Table(name="ListElements", uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class ListElement {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID", nullable=false, unique=true, length=11)
    private int id;

    @Column(name="LIST", length=30, nullable=false)
    private String list;

    @Column(name="ELEMENT", length=50, nullable=false)
    private String element;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}
