package funcionalidades;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import model.Categoria;
import repository.CategoriaRepository;
import repository.RepositorySession;

@Stateless
public class CategoriaFuncionalidade {
    @EJB
    private RepositorySession repository;
    private CategoriaRepository categoriaRepository;

    @PostConstruct
    public void initialize() {
        categoriaRepository = repository.getCategoriaRepository();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserir(Categoria timesheet) {
        categoriaRepository.inserir(timesheet);
    }

    public List<Categoria> listar() {
        return categoriaRepository.listar();
    }

    public Categoria porCodigo(Integer codigo) throws Exception {
        return categoriaRepository.porCodigo(codigo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Categoria categoria) throws Exception {
        categoriaRepository.editar(categoria);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Integer codigo) throws Exception {
        categoriaRepository.deletar(codigo);
    } 

}
