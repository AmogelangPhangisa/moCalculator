/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.momentum.calculator.common;

/**
 *
 * @author S2028873
 */
public enum PersonType {
 
    ADMINISTRATOR("Administrator"),
    CLIENT("client");

    private final String displayName;

    PersonType(String dislayName) {
        this.displayName = dislayName;
    }
    @Override
    public String toString() {
        return this.displayName;
    }
}
