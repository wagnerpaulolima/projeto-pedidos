package com.empresa.gestao_pedidos.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document(collection = "pedidos")
public class PedidoMongo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long pedidoId;  // Referência ao ID do PostgreSQL
    private String status;
    private List<ProdutoMongo> produtos;
    private Double total;

    public PedidoMongo() {
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProdutoMongo> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoMongo> produtos) {
        this.produtos = produtos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoMongo that = (PedidoMongo) o;
        return Objects.equals(pedidoId, that.pedidoId) && Objects.equals(status, that.status) && Objects.equals(produtos, that.produtos) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, status, produtos, total);
    }

    @Override
    public String toString() {
        return "PedidoMongo{" +
                "pedidoId=" + pedidoId +
                ", status='" + status + '\'' +
                ", produtos=" + produtos +
                ", total=" + total +
                '}';
    }
}