package edu.iu.habahram.ducksservice.model;

import jakarta.persistence.*;

@Entity
@Table(schema = "ducks")
public class DuckData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;

    public DuckData() {}

    public DuckData(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }

    public int id() { return id; }
    public String name() { return name; }
    public String type() { return type; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
}
