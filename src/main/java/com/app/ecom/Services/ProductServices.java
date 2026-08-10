package com.app.ecom.Services;

import com.app.ecom.Model.Product;
import com.app.ecom.Repo.ProductRepo;
import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServices {

    ProductRepo productRepo;

    public ProductServices(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll().stream()
                .map(this::mapProductsResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapProductsResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProductName(product.getProductName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setStockQuantity(product.getStockQuantity());
        productResponse.setCategory(product.getCategory());
        productResponse.setActive(product.getActive());
        return productResponse;
    }

    public ProductResponse getProductById(Long prodId) {
        return mapProductsResponse(productRepo.findById(prodId).orElse(null));

    }

    public void createProduct(ProductRequest productRequest){

        Product product = new Product();
        mapProductRequestToProduct(productRequest, product);
        productRepo.save(product);
        System.out.println(productRequest);
       // return mapProductsResponse(product);
    }

    private void mapProductRequestToProduct(ProductRequest productRequest, Product product) {
        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setActive(true);

    }

    public ProductResponse updateProduct(Long prodId, ProductRequest productRequest) {
        Product product = productRepo.findById(prodId).orElse(null);
        if (product != null) {
            mapProductRequestToProduct(productRequest, product);
            productRepo.save(product);
            return mapProductsResponse(product);
        } else {
            return null;
        }
    }

    public boolean deleteProduct(Long prodId){
        try{
        Product product = productRepo.findById(prodId).orElse(null);
        if(product !=null){
            productRepo.deleteById(prodId);
            return true;
        }
        return false;
        } catch (Exception e) {
            return false;
        }
    }
}
