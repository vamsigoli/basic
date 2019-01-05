package com.vamsi.superflix.storefront.repository;

import com.vamsi.superflix.storefront.domain.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "country", path = "country")
public interface CountryRepository   extends PagingAndSortingRepository<Country, Integer> {

}

