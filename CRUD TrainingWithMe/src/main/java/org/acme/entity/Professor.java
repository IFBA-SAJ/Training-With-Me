package org.acme.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Professor extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public UUID id;
    @NotBlank(message = "CREF não pode ser vazio")
    public String cref;
    @NotBlank(message = "CPF não pode ser vazio")
    public String cpf;
    @NotBlank(message = "Nome não pode ser vazio")
    public String nome;
    @Email
    @NotBlank(message = "Email não pode ser vazio")
    public String email;
    @Min(value = 18, message = "A idade deve ser maior ou igual a 18 anos")
    public int idade;
}