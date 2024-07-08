package com.trevis.startup.javaproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ServiceEntity")
public class ServiceEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Description")
    private String description;

    @Column(name = "Intern")
    private Boolean intern;

    @Column(name = "Name")
    private String name;

    @OneToOne() @JoinColumn(name = "ManagerId", referencedColumnName = "id")
    private UserEntity manager;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getIntern() {
        return intern;
    }
    public void setIntern(Boolean intern) {
        this.intern = intern;
    }
        
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public UserEntity getManager() {
        return manager;
    }
    public void setManager(UserEntity manager) {
        this.manager = manager;
    }


}
