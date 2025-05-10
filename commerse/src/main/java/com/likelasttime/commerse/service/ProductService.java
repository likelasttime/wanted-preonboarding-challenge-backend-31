package com.likelasttime.commerse.service;

import com.likelasttime.commerse.domain.*;
import com.likelasttime.commerse.dto.request.*;
import com.likelasttime.commerse.dto.response.CreateProductResponse;
import com.likelasttime.commerse.dto.response.ErrorBody;
import com.likelasttime.commerse.dto.response.ErrorResponse;
import com.likelasttime.commerse.exception.CommonErrorCode;
import com.likelasttime.commerse.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductPricesRepository productPricesRepository;
    private final SellersRepository sellersRepository;
    private final BrandsRepository brandsRepository;
    private final ProductCategoriesRepository productCategoriesRepository;
    private final CategoriesRepository categoriesRepository;
    private final ProductOptionGroupsRepository productOptionGroupsRepository;
    private final ProductOptionsRepository productOptionsRepository;
    private final ProductImagesRepository productImagesRepository;
    private final ProductTagsRepository productTagsRepository;
    private final TagsRepository tagsRepository;

    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        final Sellers sellers = findSellersById(createProductRequest.getSeller_id());
        final Brands brands = findBrandsById(createProductRequest.getBrand_id());

        final Products products = createProducts(createProductRequest, sellers, brands);
        createProductPrices(products, createProductRequest.getPrice());
        createProductCategories(createProductRequest.getCategories(), products);
        createProductOptionGroup(createProductRequest.getOption_groups(), products);
        createProductImages(createProductRequest.getImages(), products);
        createProductTags(createProductRequest.getTags(), products);

        return new CreateProductResponse(products.getId(), products.getName(), products.getSlug(),
                products.getCreatedAt(), products.getUpdatedAt());
    }

    private Sellers findSellersById(final Long sellerId) {
        return sellersRepository.findById(sellerId)
                .orElseThrow(() -> new ErrorResponse(false, new ErrorBody(CommonErrorCode.INVALID_INPUT)));
    }

    private Categories findCategoriesById(final Long categoryId) {
        return categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new ErrorResponse(false, new ErrorBody(CommonErrorCode.INVALID_INPUT)));
    }

    private Brands findBrandsById(final Long brandId) {
        return brandsRepository.findById(brandId)
                .orElseThrow(() -> new ErrorResponse(false, new ErrorBody(CommonErrorCode.INVALID_INPUT)));
    }

    private Tags findTagsById(final Long tagId) {
        return tagsRepository.findById(tagId)
                .orElseThrow(() -> new ErrorResponse(false, new ErrorBody(CommonErrorCode.INVALID_INPUT)));
    }

    private Products createProducts(final CreateProductRequest createProductRequest, final Sellers sellers,
                                    final Brands brands) {
        return productRepository.save(Products.createProducts(createProductRequest, sellers, brands));
    }

    private void createProductPrices(final Products products, final CreateProductPricesRequest createProductPricesRequest) {
        productPricesRepository.save(ProductPrices.createProductPrices(products, createProductPricesRequest));
    }

    private void createProductCategories(final List<CreateCategoriesRequest> createCategoriesRequest, final Products products) {
        List<ProductCategories> productCategoriesList = new ArrayList();
        for(int i=0; i<createCategoriesRequest.size(); i++) {
            Long categoryId = createCategoriesRequest.get(i).getCategory_id();
            Categories categories = findCategoriesById(categoryId);
            productCategoriesList.add(ProductCategories.createProductCategories(products, categories, createCategoriesRequest.get(i).is_primary()));
        }
        productCategoriesRepository.saveAll(productCategoriesList);
    }

    private void createProductOptionGroup(final List<CreateProductOptionGroupsRequest> createProductOptionGroupsRequest, final Products products) {
        for (CreateProductOptionGroupsRequest optionGroups : createProductOptionGroupsRequest) {
            // ProductOptionGroups 저장
            final ProductOptionGroups productOptionGroups = productOptionGroupsRepository.save(
                    ProductOptionGroups.createProductOptionGroups(products, optionGroups)
            );

            // 각 옵션 그룹에 대해 옵션들을 처리
            for (CreateOptionsRequest createOptionsRequest : optionGroups.getOptions()) {
                productOptionsRepository.save(ProductOptions.createProductOptions(productOptionGroups, createOptionsRequest));
            }
        }
    }

    private void createProductImages(final List<CreateProductImagesRequest> createProductImagesRequest, final Products products) {
        List<ProductImages> productImagesList = new ArrayList();
        for(CreateProductImagesRequest createProductImages : createProductImagesRequest) {
            productImagesList.add(ProductImages.createProductImages(products, createProductImages));
        }
        productImagesRepository.saveAll(productImagesList);
    }

    private void createProductTags(final List<Long> tagLst, final Products products) {
        for(Long tagId : tagLst) {
            final Tags tags = findTagsById(tagId);
            productTagsRepository.save(ProductTags.createProductTags(products, tags));
        }
    }
}
