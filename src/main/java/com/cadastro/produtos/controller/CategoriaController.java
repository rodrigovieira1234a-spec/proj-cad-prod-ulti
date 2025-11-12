package com.cadastro.produtos.controller;

import com.cadastro.produtos.entity.Categoria;
import com.cadastro.produtos.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Gerenciamento de categorias de produtos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // GET - Listar todas as categorias
    @GetMapping
    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista com todas as categorias cadastradas.")
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    // GET - Buscar categoria por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Busca uma categoria específica com base no ID informado.")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    // POST - Criar nova categoria
    @PostMapping
    @Operation(summary = "Criar nova categoria", description = "Cadastra uma nova categoria no sistema.")
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
        Categoria nova = categoriaService.salvar(categoria);
        return ResponseEntity.status(201).body(nova);
    }

    // PUT - Atualizar categoria
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria existente pelo ID informado.")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria atualizada = categoriaService.atualizar(id, categoria);
        return ResponseEntity.ok(atualizada);
    }

    // DELETE - Remover categoria
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar categoria", description = "Remove uma categoria existente pelo ID informado.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
