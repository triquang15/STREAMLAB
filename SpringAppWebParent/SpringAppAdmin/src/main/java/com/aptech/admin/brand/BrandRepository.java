package com.aptech.admin.brand;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.aptech.common.entity.Brand;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {
	
	public Long countById(Integer id);

	public Brand findByName(String name);
	
}
