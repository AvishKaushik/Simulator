package com.csa.simulator.components;

import java.util.Arrays;

/**
 * CPU Class - Defining the CPU here
 * This class contains the CPU and its operations
 * The CPU class is responsible for executing the instructions and handling the memory faults
 * The CPU class is also responsible for handling the registers and the memory operations
 */
public class CPU extends Converter {
    public char[] GPR0;
    public char[] GPR1;
    public char[] GPR2;
    public char[] GPR3;
    public char[] PC;
    public char[] MAR;
    public char[] MBR;
    public char[] IR;
    public char[] MFR;
    public char[] X1, X2, X3;
    public char[] CC;
    static final short LDR = 0x01;
    static final short STR = 0x02;
    static final short LDA = 0x03;
    static final short LDX = 0x21;
    static final short STX = 0x22;
    public static final short HLT = 0x00;

    public void setGPR0(short value) {
        DecimalToBinary(value, GPR0, 16);
    }

    public void setGPR1(char[] arr, int len) {
        CopyArray_src_dest(arr, GPR1, 16);
    }

    public void setGPR2(char[] arr, int len) {
        CopyArray_src_dest(arr, GPR2, 16);
    }

    public void setGPR3(char[] arr, int len) {
        CopyArray_src_dest(arr, GPR3, 16);
    }

    public void setX1(char[] arr, int len) {
        CopyArray_src_dest(arr, X1, 16);
    }

    public void setX2(char[] arr, int len) {
        CopyArray_src_dest(arr, X2, 16);
    }

    public void setX3(char[] arr, int len) {
        CopyArray_src_dest(arr, X3, 16);
    }

    public void setPC(short value) {
        DecimalToBinary(value, PC, 12);
    }

    public void setMAR(short value) {
        DecimalToBinary(value, MAR, 12);
    }

    public void setMBR(char arr[], int len) {
        CopyArray_src_dest(arr, MBR, 16);
    }

    public char[] getRegister(short rx) {
        return switch (rx) {
            case 1 -> GPR1;
            case 2 -> GPR2;
            case 3 -> GPR3;
            default -> GPR0;
        };
    }

    /**
     * Constructor for the CPU class
     * Initializes the registers and memory addresses
     */
    public CPU() {
        GPR0 = new char[16];
        GPR1 = new char[16];
        GPR2 = new char[16];
        GPR3 = new char[16];
        MAR = new char[12];
        MBR = new char[16];
        MFR = new char[4];
        IR = new char[16];
        PC = new char[12];
        CC = new char[4];
        X1 = new char[16];
        X2 = new char[16];
        X3 = new char[16];
        for (int i = 0; i < 16; i++) {
            GPR0[i] = 0;
            GPR1[i] = 0;
            GPR2[i] = 0;
            GPR3[i] = 0;
            X1[i] = 0;
            X2[i] = 0;
            X3[i] = 0;
            MBR[i] = 0;
            IR[i] = 0;
        }
        for (int i = 0; i < 12; i++) {
            PC[i] = 0;
            MAR[i] = 0;
        }
        for (int i = 0; i < 4; i++) {
            MFR[i] = 0;
            CC[i] = 0;
        }
    }


    /**
     * CopyArray_src_dest - Copies the source array to the destination array
     * @param src - Source array
     * @param des - Destination array
     * @param len - Length of the array
     */
    public void CopyArray_src_dest(char[] src, char[] des, int len) {
        if (len >= 0) System.arraycopy(src, 0, des, 0, len);
    }

    /**
     * LoadRegister - Loads the register with the effective address
     * @param rx - Register
     * @param EA - Effective Address
     * @param m - Memory
     */
    private void LoadRegister(char[] rx, short EA, Memory m) {
        byte RValue = (byte) BinaryToDecimal(rx, 2);
        DecimalToBinary(EA, MAR, 12);
        DecimalToBinary(m.Data[EA], MBR, 16);
        switch (RValue) {
            case 0:
                CopyArray_src_dest(MBR, GPR0, 16);
                break;
            case 1:
                CopyArray_src_dest(MBR, GPR1, 16);
                break;
            case 2:
                CopyArray_src_dest(MBR, GPR2, 16);
                break;
            case 3:
                CopyArray_src_dest(MBR, GPR3, 16);
                break;
        }
    }


    /**
     * LoadIndexRegister - Loads the index register with the effective address
     * @param ix - Index Register
     * @param EA - Effective Address
     * @param m - Memory
     */
    private void LoadIndexRegister(char[] ix, short EA, Memory m) {
        byte RVal = (byte) BinaryToDecimal(ix, 2);
        DecimalToBinary(EA, MAR, 12);
        DecimalToBinary(m.Data[EA], MBR, 16);
        switch (RVal) {
            case 1:
                CopyArray_src_dest(MBR, X1, 16);
                break;
            case 2:
                CopyArray_src_dest(MBR, X2, 16);
                break;
            case 3:
                CopyArray_src_dest(MBR, X3, 16);
                break;
            default:
                break;
        }
    }

    /**
     * StoreRegisterwithEA - Stores the register with the effective address
     * @param rx - Register
     * @param EA - Effective Address
     */
    private void StoreRegisterwithEA(char[] rx, short EA) {
        byte RVal = (byte) BinaryToDecimal(rx, 2);
        char[] R_x = getRegister((short) RVal);
        DecimalToBinary(EA, MAR, 12);
        ReverseCopy_Array_src_dst(MAR, R_x, 16, 12);
    }

    /**
     * ReverseCopy_Array_src_dst - Copies the source array to the destination array in reverse order
     * @param src - Source array
     * @param des - Destination array
     * @param length - Length of the array
     * @param srclen - Length of the source array
     */
    public void ReverseCopy_Array_src_dst(char[] src, char[] des, int length, int srclen) {
        for (int i = 0; i < srclen; i++)
            des[length - i - 1] = src[srclen - i - 1];
    }

    /**
     * RStoretoMem - Stores the register to memory
     * @param rx - Register
     * @param EA - Effective Address
     * @param m - Memory
     */
    private void RStoretoMem(char[] rx, short EA, Memory m) {
        byte RVal = (byte) BinaryToDecimal(rx, 2);
        char[] R_x = getRegister((short) RVal);
        DecimalToBinary(EA, MAR, 12);
        CopyArray_src_dest(R_x, MBR, 16);
        if (EA >= 0 && EA <= 9) {
            MFR[3] = 1;
            m.Data[4] = BinaryToDecimal(PC, 12);
            return;
        }
        m.Data[EA] = BinaryToDecimal(MBR, 16);
    }

    /**
     * IXStoretoMem - Stores the index register to memory
     * @param ix - Index Register
     * @param EA - Effective Address
     * @param m - Memory
     */
    private void IXStoretoMem(char[] ix, short EA, Memory m) {
        byte XVal = (byte) BinaryToDecimal(ix, 2);
        DecimalToBinary(EA, MAR, 12);
        switch (XVal) {
            case 0:
                break;
            case 1:
                CopyArray_src_dest(X1, MBR, 16);
                break;
            case 2:
                CopyArray_src_dest(X2, MBR, 16);
                break;
            case 3:
                CopyArray_src_dest(X3, MBR, 16);
                break;
        }
        if (EA >= 0 && EA <= 9) {
            MFR[3] = 1;
            m.Data[4] = BinaryToDecimal(PC, 12);
            return;
        }
        m.Data[EA] = BinaryToDecimal(MBR, 16);
    }

    /**
     * Reset - Resets the CPU and memory
     * @param m - Memory
     */
    public void Reset(Memory m) {
        for (int i = 0; i < 16; i++) {
            GPR0[i] = 0;
            GPR1[i] = 0;
            GPR2[i] = 0;
            GPR3[i] = 0;
            X1[i] = 0;
            X2[i] = 0;
            X3[i] = 0;
            IR[i] = 0;
            MBR[i] = 0;
        }
        for (int i = 0; i < 12; i++) {
            MAR[i] = 0;
            PC[i] = 0;
        }
        for (int i = 0; i < 4; i++) {
            MFR[i] = 0;
            CC[i] = 0;
        }
        Arrays.fill(m.Data, (short) 0);
    }

    /**
     * get_Effective_Address - Gets the effective address
     * @param ix - Index Register
     * @param addr - Address
     * @param m - Memory
     * @param I - I bit
     * @return - Returns the effective address
     */
    private short get_Effective_Address(char[] ix, char[] addr, Memory m, char I) {
        byte IX_Val = (byte) BinaryToDecimal(ix, 2);
        short EA = BinaryToDecimal(addr, 5);
        switch (IX_Val) {
            case 0:
                break;
            case 1:
                EA += BinaryToDecimal(X1, 16);
                break;
            case 2:
                EA += BinaryToDecimal(X2, 16);
                break;
            case 3:
                EA += BinaryToDecimal(X3, 16);
                break;
            default:
                break;
        }
        if (I == 1) {
            m.Data[6] = EA;
            return m.Data[EA];
        }
        return EA;
    }

    /**
     * Execute - Executes the instruction
     * @param m - Memory
     */
    public void Execute(Memory m) {
        char[] bin_opcode = new char[6];
        System.arraycopy(IR, 0, bin_opcode, 0, 6);
        char[] RX = new char[2];
        System.arraycopy(IR, 6, RX, 0, 2);
        char[] IX = new char[2];
        System.arraycopy(IR, 8, IX, 0, 2);
        char I = IR[10];
        char[] Address = new char[5];
        System.arraycopy(IR, 11, Address, 0, 5);
        char[] Count = new char[4];
        System.arraycopy(IR, 12, Count, 0, 4);
        short OpCode = BinaryToDecimal(bin_opcode, 6); // Converts binary opcode to decimal OpCode Value
        short EA = get_Effective_Address(IX, Address, m, I);
        try {
            switch (OpCode) {
                case HLT:
                    break;
                case LDR:
                    LoadRegister(RX, EA, m);
                    break;
                case STR:
                    RStoretoMem(RX, EA, m);
                    break;
                case LDA:
                    StoreRegisterwithEA(RX, EA);
                    break;
                case LDX:
                    LoadIndexRegister(IX, EA, m);
                    break;
                case STX:
                    IXStoretoMem(IX, EA, m);
                    break;
                default:
                    MFR[1] = 1;
                    m.Data[4] = BinaryToDecimal(PC, 12);
                    break;
            }
        } catch (IndexOutOfBoundsException ioobe) {
            MFR[0] = 1;
            m.Data[4] = BinaryToDecimal(PC, 12);
        }
        m.Data[1] = BinaryToDecimal(MFR, 4);
    }

    /**
     * MFHandle - Handles the memory fault
     * @param mem - Memory
     */
    public void MFHandle(Memory mem) {
        if (MFR[0] == 1) {
            DecimalToBinary((short) 10, PC, 12);
        }
        MFR[0] = 0;
        MFR[1] = 0;
        MFR[2] = 0;
        MFR[3] = 0;
        mem.Data[4] = BinaryToDecimal(PC, 12);
    }
}
