package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;
    private Supplier defaultSupplier;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
        defaultSupplier = new Supplier("All", "");
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        supplier.setId(data.size() + 1);
        data.add(supplier);
    }

    @Override
    public Supplier find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public void removeAll() {
        data.clear();
    }

    @Override
    public List<Supplier> getAll() {
        return data;
    }

    @Override
    public Integer findIdByName(String name){
        for ( Supplier supp : data){
            if (name.equals(supp.getName())){
                return supp.getId();
            }
        }
        return null;
    }

    @Override
    public Supplier getDefaultSupplier(){
        return defaultSupplier;
    }

    @Override
    public List<Product> filterProducts(List<Product> products, Supplier supplier){
        if (supplier.equals(defaultSupplier)) {
            return products;
        }
        List<Product> temp = new ArrayList<>();
        for (Product product : products) {
            if (product.getSupplier().equals(supplier)) {
                temp.add(product);
            }
        }
        return temp;
    }
}
