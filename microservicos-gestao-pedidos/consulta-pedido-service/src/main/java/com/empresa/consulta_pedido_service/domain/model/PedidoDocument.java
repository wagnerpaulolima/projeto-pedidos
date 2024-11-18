package com.empresa.consulta_pedido_service.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document(collection = "pedidos")
public class PedidoDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String status;
    private Double valorTotal;
    private List<Produto> produtos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        PedidoDocument that = (PedidoDocument) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(valorTotal, that.valorTotal) && Objects.equals(produtos, that.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, valorTotal, produtos);
    }

    @Override
    public String toString() {
        return "PedidoDocument{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", valorTotal=" + valorTotal +
                ", produtos=" + produtos +
                '}';
    }
}
