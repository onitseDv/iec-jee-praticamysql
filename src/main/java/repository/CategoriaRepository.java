package repository;

import java.util.List;

import model.Categoria;

public interface CategoriaRepository {
    void inserir(Categoria categoria);
    List<Categoria> listar();

    Categoria porCodigo(Integer id) throws Exception;

    void editar(Categoria categoria) throws Exception;

    void deletar(Integer codigo) throws Exception;
}
