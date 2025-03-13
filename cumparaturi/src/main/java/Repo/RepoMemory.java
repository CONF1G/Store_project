package Repo;

import Domain.Product;

import java.util.ArrayList;
import java.util.List;

public class RepoMemory {
    protected List<Product> repo = new ArrayList<>();

    public RepoMemory() {
    }

    public RepoMemory(List<Product> repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo;
    }

    public void add(Product product) {
        repo.add(product);
    }

    public Product get(int pos) {
        return repo.get(pos);
    }

    public void update(Product product) {
        for (int i = 0; i < repo.size(); i++) {
            if (repo.get(i).getId() == product.getId()) {
                repo.set(i, product);
                return;
            }
        }
        throw new IllegalArgumentException("Product with ID " + product.getId() + " not found.");
    }
}
