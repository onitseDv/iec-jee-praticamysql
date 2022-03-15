package funcionalidades
;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import model.Produto;
import repository.ProdutoRepository;
import repository.RepositorySession;

@Stateless
public class ProdutoFuncionalidade {

    @EJB
    private RepositorySession repository;
    private ProdutoRepository produtoRepository;

    @PostConstruct
    public void initialize() {
        this.produtoRepository = repository.getProdutoRepository();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserir(Produto produto) {
        this.produtoRepository.inserir(produto);
    }

    public List<Produto> listar() {
        return this.produtoRepository.listar();
    }

    public List<Produto> listarPorCategoria(Integer codigoCategoria) {
        return this.produtoRepository.listarPorCategoria(codigoCategoria);
    }

    public List<Produto> listarPorNomeComecandoCom(String nome) {
        return this.produtoRepository.listarPorNomeComecandoCom(nome);
    }

    public Produto porCodigo(int codigo) throws Exception {
        return this.produtoRepository.porCodigo(codigo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Produto produto) throws Exception {
        this.produtoRepository.editar(produto);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(int codigo) throws Exception {
        this.produtoRepository.deletar(codigo);
    }
    
}
