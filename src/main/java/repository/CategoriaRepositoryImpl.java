package repository;

import java.util.List;

import javax.persistence.EntityManager;

import model.Categoria;

public class CategoriaRepositoryImpl implements CategoriaRepository {
    private EntityManager em;
    public CategoriaRepository setEntityManager(EntityManager em) {
        this.em = em;
        return this;
    }

    @Override
    public void inserir(Categoria categoria) {
        em.persist(categoria);
    }

    @Override
    public List<Categoria> listar() {
        return em.createQuery("select c from Categoria c", Categoria.class)
            .getResultList();
    }

    @Override
    public Categoria porCodigo(Integer id) throws Exception {
        return em.find(Categoria.class, id);
    }

    @Override
    public void editar(Categoria categoria) throws Exception {
        em.merge(categoria);
    }
    
    @Override
    public void deletar(Integer codigo) throws Exception {
        Categoria categoria = em.find(Categoria.class, codigo);
        em.remove(categoria);
    }
}
