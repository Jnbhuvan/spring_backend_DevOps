package com.app.ecom.Controller;

import com.app.ecom.Services.ProductServices;
import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(ProductRequest productRequest){
        return new ResponseEntity<>(productServices.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<?> getProductById(@PathVariable Long prodId) {
        ProductResponse productResponse = productServices.getProductById(prodId);
        if (productResponse != null) {
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found1", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest){
        productServices.createProduct(productRequest);
        return new ResponseEntity<>("Product Added successfully!", HttpStatus.OK);
    }

    @PutMapping("/product/{prodId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long prodId, @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productServices.updateProduct(prodId, productRequest);
        if(productResponse !=null){
            return new ResponseEntity<>("Product Added successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("ProductId not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long prodId){
        if(productServices.deleteProduct(prodId)){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword){
        return new ResponseEntity<>(productServices.searchProducts(keyword), HttpStatus.OK);
    }
}
