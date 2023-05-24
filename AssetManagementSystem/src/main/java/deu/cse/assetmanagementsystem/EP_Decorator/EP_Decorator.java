/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.cse.assetmanagementsystem.EP_Decorator;

/**
 *
 * @author 이가연
 */
public abstract class EP_Decorator implements EP_Component {
    protected EP_Component decoratedComponent;

    public EP_Decorator(EP_Component decoratedComponent) {
        this.decoratedComponent = decoratedComponent;
    }

    @Override
    public void registerExpenditure(String amount,String EP,String category) {
        decoratedComponent.registerExpenditure(amount,EP,category);
    }
}