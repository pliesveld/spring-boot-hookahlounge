package tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tutorial.domain.Product;
import tutorial.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            products.add(product);
        }
        return products;
    }

    public Optional<Product> findProduct(String productName) {
        return productRepository.findById(productName);
    }

    public boolean createProduct(String productName) {
        if (productRepository.findById(productName).isPresent())
            return false;

        Product product = new Product();
        product.setName(productName);
        productRepository.save(product);
        return true;
    }
}
