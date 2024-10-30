package com.csa.simulator.components;

public class Assembler extends Converter {
    public String getOpCode(String op){
        return switch (op) {
            case "LDR" -> "000001";
            case "STR" -> "000010";
            case "LDA" -> "000011";
            case "LDX" -> "100001";
            case "STX" -> "101010";
            case "JZ" -> "001000";
            case "JNE" -> "001001";
            case "JCC" -> "001010";
            case "JMA" -> "001011";
            case "JSR" -> "001100";
            case "RFS" -> "001101";
            case "JGE" -> "001111";
            case "AMR" -> "000100";
            case "SMR" -> "000101";
            case "AIR" -> "000110";
            case "SIR" -> "000111";
            default -> "none";
        };
    }
}