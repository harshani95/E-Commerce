package com.devstack.ecom.controller;

import com.devstack.ecom.dto.request.RequestProductDto;
import com.devstack.ecom.service.ProductService;
import com.devstack.ecom.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<StandardResponse> create(@RequestBody RequestProductDto productDto) {
        productService.create(productDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Product was created!..",null),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> get(@PathVariable String id) {
        return new ResponseEntity<>(
                new StandardResponse(200,"Product data!..",productService.findById(id)),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> update(@PathVariable String id,
                                                   @RequestBody RequestProductDto productDto) {
        productService.update(id,productDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Product was updated!..",null),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> delete(@PathVariable String id) {
        productService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Product was deleted!..",null),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> getAll(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ) {

        return new ResponseEntity<>(
                new StandardResponse(200,"Product list!..",
                        productService.findAll(searchText, page, size)),
                HttpStatus.OK
        );
    }
}
