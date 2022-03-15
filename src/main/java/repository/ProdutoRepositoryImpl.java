package repository;

import java.util.List;

import javax.persistence.EntityManager;

import model.Produto;

public class ProdutoRepositoryImpl implements ProdutoRepository {

    private EntityManager em;

    public ProdutoRepository setEntityManager(EntityManager em) {
        this.em = em;
        return this;
    }

    @Override
    public void inserir(Produto produto) {
        this.em.persist(produto);
    }

    @Override
    public List<Produto> listar() {
        return this.em.createQuery("select p from Produto p", Produto.class)
            .getResultList();
    }

    @Override
    public Produto porCodigo(Integer id) throws Exception {
        return this.em.find(Produto.class, id);
    }

    @Override
    public void editar(Produto produto) throws Exception {
        this.em.merge(produto);
    }

    @Override
    public void deletar(Integer codigo) throws Exception {
        Produto produto = this.em.find(Produto.class, codigo);
        this.em.remove(produto);
    }

    @Override
    public List<Produto> listarPorCategoria(Integer codigoCategoria) {
        return this.em.createQuery("select p from Produto p where p.categoria.codigo = :codCategoria", Produto.class)
        .setParameter("codCategoria", codigoCategoria).getResultList();
    }

    @Override
    public List<Produto> listarPorNomeComecandoCom(String nome) {
        return this.em.createQuery("select p from Produto p where lower(p.nome) LIKE lower(:nome)", Produto.class)
        .setParameter("nome", nome + "%").getResultList();
    }
    
}
