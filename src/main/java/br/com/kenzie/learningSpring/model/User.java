package br.com.kenzie.learningSpring.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

import static java.lang.String.format;

@Entity
@Table(name="users")

public class User {
    public User(){

    }
    public User(String name, String cpf, String email, String password, String type) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 92, nullable = false)
    private String name;
    @Column(length = 11, nullable = false, unique = true)

    private String cpf;
    @Column(length = 62, nullable = false, unique = true)

    private String email;
    @Column(columnDefinition = "TEXT",nullable = false)

    private String password;
    @Column(length = 6, nullable = false)

    private String type;
    @Column(columnDefinition = "DECIMAL")
    @ColumnDefault("0")
    private float balance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User" + "{"  +
                "name: " +  getName() +
                "cpf: " + getCpf() +
                "email: " + getEmail()  +
                "balance: " + getBalance() +
                "type: "  + getType() +
                "}";

    }
}
