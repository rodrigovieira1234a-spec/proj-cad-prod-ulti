package com.cadastro.produtos.controller;

import com.cadastro.produtos.entity.Produto;
import com.cadastro.produtos.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Gerenciamento dos produtos do sistema")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // GET - Listar todos os produtos
    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista com todos os produtos cadastrados no sistema.")
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    // GET - Buscar produto por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Busca um produto específico com base no ID informado.")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    // POST - Criar novo produto
    @PostMapping
    @Operation(summary = "Criar novo produto", description = "Cadastra um novo produto no sistema.")
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto novo = produtoService.salvar(produto);
        return ResponseEntity.status(201).body(novo);
    }

    // PUT - Atualizar produto
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza as informações de um produto existente pelo ID informado.")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Produto atualizado = produtoService.atualizar(id, produto);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE - Remover produto
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto", description = "Remove um produto existente pelo ID informado.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
