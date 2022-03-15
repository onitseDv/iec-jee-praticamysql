package repository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RepositorySessionImpl implements RepositorySession{    
    @PersistenceContext
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public CategoriaRepository getCategoriaRepository(){
        return new CategoriaRepositoryImpl().setEntityManager(em);
    }

    @Override
    public ProdutoRepository getProdutoRepository() {
        return new ProdutoRepositoryImpl().setEntityManager(em);
    }

}
