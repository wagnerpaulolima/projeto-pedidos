package com.empresa.pedido_service.domain.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;
import java.util.Objects;

@Document(collection = "produtos")
public class ProdutoMongo  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidade;

    public ProdutoMongo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoMongo that = (ProdutoMongo) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(preco, that.preco) && Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco, quantidade);
    }

    @Override
    public String toString() {
        return "ProdutoMongo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }
}
