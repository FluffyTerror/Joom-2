package com.FluffyTerror.Joom2.service.product;

import com.FluffyTerror.Joom2.dto.ImageDto;
import com.FluffyTerror.Joom2.dto.ProductDto;
import com.FluffyTerror.Joom2.exceptions.AlreadyExistsException;
import com.FluffyTerror.Joom2.exceptions.ProductNotFoundException;
import com.FluffyTerror.Joom2.model.Category;
import com.FluffyTerror.Joom2.model.Image;
import com.FluffyTerror.Joom2.model.Product;
import com.FluffyTerror.Joom2.repository.CategoryRepository;
import com.FluffyTerror.Joom2.repository.ImageRepository;
import com.FluffyTerror.Joom2.repository.ProductRepository;
import com.FluffyTerror.Joom2.request.AddProductRequest;
import com.FluffyTerror.Joom2.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;


    @Override
    public Product addProduct(AddProductRequest request) {
        //делаем проверку на наличие категории в бд
        // если категория есть то ставим эту категорию новому продукту
        //если нет то сохраняем как новую категорию
        //и ставим ее для нашего продукта

        if (productExists(request.getName(),request.getBrand())){
                throw new AlreadyExistsException(request.getBrand()+" "+request.getName()+ " "+"already exists, you may update this product instead!");
        }
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);

                });
        request.setCategory(category);
        return productRepository.save(createProduct(request,category));
    }

    private boolean productExists(String name,String brand){
        return productRepository.existsByNameAndBrand(name,brand);
    }

    private Product createProduct(AddProductRequest request, Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getAmount(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,()->{throw new ProductNotFoundException("Product not found");});

    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
    return productRepository.findById(productId).map(existingProduct->updateExistingProduct(existingProduct,request))
            .map(productRepository::save).orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setAmount(request.getAmount());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }
    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products){
        return products.stream().map(this::convertToDto).toList();


    }

    @Override
    public ProductDto convertToDto(Product product){
        ProductDto productDto = modelMapper.map(product,ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos= images.stream()
                .map(image ->modelMapper.map(image,ImageDto.class))
                .toList();
        productDto.setImages(imageDtos);
        return productDto;
    }
}
