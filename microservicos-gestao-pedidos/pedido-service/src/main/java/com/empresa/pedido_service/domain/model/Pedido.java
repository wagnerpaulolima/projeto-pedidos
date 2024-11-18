package com.empresa.pedido_service.domain.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Double valorTotal;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(status, pedido.status) && Objects.equals(valorTotal, pedido.valorTotal) && Objects.equals(produtos, pedido.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, valorTotal, produtos);
    }
}
