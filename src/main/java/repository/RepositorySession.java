package repository;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Local
public interface RepositorySession {
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public CategoriaRepository getCategoriaRepository();

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public ProdutoRepository getProdutoRepository();
}


