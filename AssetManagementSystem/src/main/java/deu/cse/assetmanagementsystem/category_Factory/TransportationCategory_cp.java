package deu.cse.assetmanagementsystem.category_Factory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 이가연
 */
public class TransportationCategory_cp implements Category_product {
        public String getName() {
        return "교통비";
    }
     @Override
    public String toString() {
        return getName();
    }
}