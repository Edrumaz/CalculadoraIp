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

    private String[] getNetwork (int diff) {
        int count = 0, aux = 0;
        String binaries = this.ipToBinary(this.ip), network = "";

        for (int i = 0; i < 32; i++) {
            if (binaries.charAt(i + aux) == '.') {
                aux += 1;
            }

            network += (i > 32 - diff ) ? "0" : binaries.charAt(aux + i);
            count += 1;

            if (count == 8) {
                network += ".";
                count = 0;
            }
        }

        String output[] = { network, this.binaryToIp(network) };

        return output;
    }

    private String[] getBroadcast (int diff) {
        int count = 0, aux = 0;
        String binaries = this.ipToBinary(this.ip), network = "";

        for (int i = 0; i < 32; i++) {
            if (binaries.charAt(i + aux) == '.') {
                aux += 1;
            }

            network += (i >= 32 - diff ) ? "1" : binaries.charAt(aux + i);
            count += 1;

            if (count == 8) {
                network += ".";
                count = 0;
            }
        }

        String output[] = { network, this.binaryToIp(network) };

        return output;
    }

    private String[] getHosts (String netmask, int diff) {
        int count = 0, enable = 0, disable = 0;
        netmask = this.ipToBinary(netmask);
        for (char i : netmask.toCharArray()) {
            if (count >= 32 - diff) {
                if (i == '0') {
                    disable ++;
                }
            } else {
                if (i == '1') {
                    enable ++;
                }
            }
            count ++;
        }

        String hostPart = "" + (Math.pow(2, disable) - 2);
        String netPart = "" + Math.pow(2, enable);

        String [] output = { hostPart, netPart };

        return  output;
    }

    public String[] geInfo() {
        String netmask = "", wildcard = "";
        int diff = 32 - this.mask, oc = 0, oc2 = 0;

        if (diff > 0 && diff <= 8) {
            oc = this.getByte(diff, 8, true);
            oc2 = this.getByte(diff, 8, false);
            netmask = "255.255.255." + oc + ".";
            wildcard = "0.0.0." + oc2 + ".";
        } else if (diff > 8 && diff <= 16) {
            oc = this.getByte(diff, 16, true);
            oc2 = this.getByte(diff, 16, false);
            netmask = "255.255." + oc + ".0.";
            wildcard = "0.0." + oc2 + ".255.";
        } else if (diff > 16 && diff <= 24) {
            oc = this.getByte(diff, 24, true);
            oc2 = this.getByte(diff, 24, false);
            netmask = "255." + oc + ".0.0.";
            wildcard = "0." + oc2 + ".255.255.";
        } else if (diff > 24 && diff < 32) {
            oc = this.getByte(diff, 32, true);
            oc2 = this.getByte(diff, 32, false);
            netmask = oc + ".0.0.0.";
            wildcard = oc2 + ".255.255.255.";
        } else {
            netmask = "invalid IP";
        }


        String network[] = this.getNetwork(diff);
        String broadcast[] = this.getBroadcast(diff);
        String host[] = this.getHosts(netmask, diff);

        String[] output = { netmask + " (" + this.ipToBinary(netmask) + ")", wildcard + " (" + this.ipToBinary(wildcard) + ")", network[1] + " (" + this.ipToBinary(network[1]) + ")", broadcast[1] + " (" + this.ipToBinary(broadcast[1]) + ")", host[0], host[1] };

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

    private String ipToBinary (String ip) {
        String binaries = "", _byte = ""; int count = 0;
        for (String oc : ip.split("\\.")) {
            _byte = Integer.toBinaryString(Integer.parseInt(oc));
            if (_byte.length() != 8) {
                int a = 8 - _byte.length(); String auxiliar = "";
                for (int i = 0; i < a; i++) {
                    auxiliar += "0";
                }
                _byte = auxiliar + _byte;
            }
            count ++;
            binaries += _byte + ".";
        }
        return  binaries;
    }

    private String binaryToIp (String binary) {
        int top = 0, count = 0; String ip = "";
        for (String octant : binary.split("\\.", 0)) {
            top = 7; count = 0;
            for (char bit : octant.toCharArray()) {
                count += (bit == '1') ? Math.pow(2, top) : 0 ;
                top --;
            }
            ip = ip + "" + count + ".";
        }
        return ip;
    }
}
