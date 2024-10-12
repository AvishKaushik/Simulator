package com.csa.simulator.components;

/**
 * Memory Class - Defining the memory block here
 *
 * @author Avish Kaushik
 * @version 1.0
 */
public class Memory
{
    public short[] Data;
    static int KB2 = 1024 * 2;
    public Memory(){

        Data = new short[KB2];
        for(int i=0;i<KB2;i++){
            Data[i] = 0;
        }
    }
}