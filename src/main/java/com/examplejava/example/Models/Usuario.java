package com.examplejava.example.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Getter @Setter @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter  @Column(name = "nombre")
    private String nombre;
    @Getter @Setter  @Column(name = "apellido")
    private String apellido;
    @Getter @Setter  @Column(name = "email")
    private String email;
    //@Getter @Setter  @Column(name = "telefono")
    //private String telefono;
    @Getter @Setter  @Column(name = "password")
    private String password;

}
