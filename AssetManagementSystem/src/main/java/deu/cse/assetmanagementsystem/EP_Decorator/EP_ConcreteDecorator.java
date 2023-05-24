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
class EP_ConcreteDecorator extends EP_Decorator {
    public EP_ConcreteDecorator(EP_Component decoratedComponent) {
        super(decoratedComponent);
    }

    @Override
    public void registerExpenditure(String amount,String EP,String category) {
        System.out.println("로그 -> " + amount);
        super.registerExpenditure(amount,EP,category);
    }
}