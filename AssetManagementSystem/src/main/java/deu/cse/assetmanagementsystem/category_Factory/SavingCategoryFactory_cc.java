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
public class SavingCategoryFactory_cc extends CategoryFactory_creator {
    public Category_product createCategory() {
        return new SavingCategory_cp();
    }
}