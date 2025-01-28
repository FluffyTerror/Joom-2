package com.FluffyTerror.Joom2.service.product;

import com.FluffyTerror.Joom2.model.Product;
import com.FluffyTerror.Joom2.request.AddProductRequest;
import com.FluffyTerror.Joom2.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(UpdateProductRequest product, Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String brand,String name);

    Long countProductsByBrandAndName(String brand,String name);

}