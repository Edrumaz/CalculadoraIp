package com.example.alejandro.calculadoraip.Classes;

/*
 * Created by Alejandro on 04/04/2018.
*/

public class Calc {

    private int mask = 0;
    private String ip = "";
    private final static int[] aux = { 1, 2, 4, 8, 16, 32, 64, 128 };

    public Calc (String ip, int mask) {
        this.ip = ip;
        this.mask = mask;
    }

    public int getNetwork (int diff) {
        int aux = 0;
        String array[] = this.ip.split("."), binaries = "", network = "";

        for (int i = 0; i < array.length - 1; i++) {
            binaries += Integer.toBinaryString(Integer.parseInt(array[i])) + ((i == array.length - 1) ? "" : ".");
        }

        for (int i = 0; i < 32; i++) {
           if (binaries.charAt(i) == '.') {
               aux += 1;
           }

           network += (i > 32 - diff) ? "0" : binaries.charAt(aux + i);
        }

        return 0;
    }

    public String[] geInfo() {
        String netmask = "", wildcard = "";
        int diff = 32 - this.mask, oc = 0, oc2 = 0;

        if (diff > 0 && diff <= 8) {
            oc = this.getByte(diff, 8, true);
            oc2 = this.getByte(diff, 8, false);
            netmask = "255.255.255." + oc;
            wildcard = "0.0.0." + oc2;
        } else if (diff > 8 && diff <= 16) {
            oc = this.getByte(diff, 16, true);
            oc2 = this.getByte(diff, 16, false);
            netmask = "255.255." + oc + ".0";
            wildcard = "0.0." + oc2 + ".255";
        } else if (diff > 16 && diff <= 24) {
            oc = this.getByte(diff, 24, true);
            oc2 = this.getByte(diff, 24, false);
            netmask = "255." + oc + ".0.0";
            wildcard = "0." + oc2 + ".255.255";
        } else if (diff > 24 && diff < 32) {
            oc = this.getByte(diff, 32, true);
            oc2 = this.getByte(diff, 32, false);
            netmask = oc + ".0.0.0";
            wildcard = oc2 + ".255.255.255";
        } else {
            netmask = "invalid IP";
        }

        String[] output = { netmask, wildcard };

        int a = this.getNetworkHost(diff);
        return output;
    }

    private int getByte (int bits, int interval, boolean flag) {
        int _byte = 0, count = Math.abs(interval - bits);
        if (flag) {
            for (int i = 7; i >= (8 - count); i--) {
                _byte += this.aux[i];
            }
        } else {
            for (int i = 0; i < (8 - count); i++) {
                _byte += this.aux[i];
            }
        }
        return _byte;
    }
}
