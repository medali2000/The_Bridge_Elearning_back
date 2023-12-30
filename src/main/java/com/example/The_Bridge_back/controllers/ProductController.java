package com.example.The_Bridge_back.controllers;

import com.example.The_Bridge_back.entities.product;
import com.example.The_Bridge_back.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping({"/products"})
@CrossOrigin(origins="*")

public class ProductController {
    @Autowired
    ProductService productService ;

    @GetMapping("/")
    public List<product> getAllProviders() {
        //return (List<provider>) providerRepository.findAll();
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public product createProvider( @RequestBody product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{idProduct}")
    public product updateProvider(@PathVariable Long idProduct, @RequestBody product productRequest) {
        return productService.updateProduct(idProduct,productRequest);
    }

    @DeleteMapping("/{productId}")
    public product deleteProvider(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    public product getProvider(@PathVariable Long productId) {
        return productService.getOneProductById(productId);
    }

    @PostMapping("/uploadImage/{idProduct}")
    public product uploadImage(@RequestParam("image" ) MultipartFile multipartFile , @PathVariable Long idProduct )  throws IOException {

        return productService.uploadImage(idProduct , multipartFile);
    }

    @GetMapping(value = "/downloadImage/{idProduct}" , produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] downloadImage( @PathVariable Long idProduct ){
        return productService.downloadImage(idProduct);
    }
}
