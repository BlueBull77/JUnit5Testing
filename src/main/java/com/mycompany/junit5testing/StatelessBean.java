package com.mycompany.junit5testing;

import javax.ejb.Stateless;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marek
 */
@Stateless
public class StatelessBean {

    public int square(int a) {
        return a * a;
    }
}
