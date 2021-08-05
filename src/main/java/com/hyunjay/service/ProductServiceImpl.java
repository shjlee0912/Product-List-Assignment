package com.hyunjay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyunjay.model.Product;
import com.hyunjay.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository pr;
	@Override
	public void create(Product prd) {
		// TODO Auto-generated method stub
		pr.save(prd);
	}

	@Override
	public Product getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return pr.findById(id).orElseThrow(() -> new Exception("Invalid id"));
	}

	@Override
	public void update(Long id, Product prd) {
		// TODO Auto-generated method stub
		Optional<Product> prod = pr.findById(id);
		if(prod.isPresent()) {
			Product p = prod.get();
			p.setName(prd.getName());
			p.setPrice(prd.getPrice());
			p.setQuantity(prd.getQuantity());
			pr.save(p);
		}
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		pr.deleteById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return pr.findAll();
	}

}
