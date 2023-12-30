package com.example.The_Bridge_back.services;

import com.example.The_Bridge_back.configurations.ImageUtils;
import com.example.The_Bridge_back.entities.imageModel;
import com.example.The_Bridge_back.entities.product;
import com.example.The_Bridge_back.repositories.ProductRepository;
import com.example.The_Bridge_back.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    StorageRepository storageRepository;
    public List<product> getAllProducts(){
        List<product> products = productRepository.findAll();
        Collections.reverse(products);
        return products;
    }

    public product getOneProductById(Long idProvider){
        return productRepository.findById(idProvider).orElseThrow(()->new IllegalArgumentException());
    }

    public  product saveProduct(product product){
        return productRepository.save(product);
    }

    public product deleteProduct(long idProduct)
    {
        product temp = null ;
        Optional<product> opt = productRepository.findById(idProduct);
        if(opt.isPresent())
        {
            temp= opt.get();
            productRepository.delete(temp);
            return temp;
        }
        if(temp==null) throw new IllegalArgumentException("provider with id = " + idProduct+ " not found");
        return temp;
    }

    public product updateProduct(long idProduct , product product){
        product temp = null ;
        Optional<product> opt = productRepository.findById(idProduct);
        if(opt.isPresent())
        {
            temp= opt.get();
            temp.setTitle(product.getTitle());
            temp.setPrice(product.getPrice());
            temp.setImage(product.getImage());
            productRepository.save(product);
            return temp;
        }
        if(temp==null) throw new IllegalArgumentException("provider with id = " + idProduct+ " not found");
        System.out.print(temp);
        return temp;
    }



    public product uploadImage(Long  idProduct , MultipartFile multipartFile)  throws IOException {
        product product = productRepository.findById(idProduct).get();
        imageModel imageProduct= product.getImageModel();
        if(imageProduct!=null){
            product.setImageModel(null);
            storageRepository.delete(imageProduct);
        }
        imageModel imageData = storageRepository.save(imageModel.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .picByte(ImageUtils.compressImage(multipartFile.getBytes())).build());
        if(imageData != null) {
            product.setImageModel(imageData);
        }
        return productRepository.save(product) ;
    }


    public byte[] downloadImage(Long idProduct ) {
        product product = productRepository.findById(idProduct).get();
        imageModel imageModel = product.getImageModel();
        Optional<imageModel> dbImageData = Optional.ofNullable(imageModel) ;
        byte[] image = ImageUtils.decompressImage(dbImageData.get().getPicByte());
        return image;
    }
}
