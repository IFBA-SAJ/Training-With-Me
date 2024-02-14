package org.acme.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Exercicio extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public UUID id;
    @NotBlank(message = "Tipo não pode ser vazio")
    public String tipo;
    @NotBlank(message = "Descrição não pode ser vazia")
    public String descricao;
    @Min(value = 1, message = "Mínimo de uma série")
    public int serie;
    @Min(value = 1, message = "Mínimo de uma repetição")
    public int repeticao;
    @Min(value = 0, message = "A carga deve ser maior ou igual a 0 quilos")
    public int carga;
}