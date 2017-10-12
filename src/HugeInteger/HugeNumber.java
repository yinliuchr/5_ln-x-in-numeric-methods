package HugeInteger;

import java.util.Objects;

/**
 * Created by liuyin14 on 2016/12/6.
 */
public class HugeNumber {
    private String integer;                                            //整数部分
    private String decimal;                                            //小数部分
    private boolean isPositive;

    public HugeNumber() {
        integer = "0";
        decimal = "0";
        isPositive = true;    //0记作正数
    }
    public HugeNumber(String inte, String decimal, boolean p) {
        String in = inte;
        while(in.charAt(0) == '0' && in.length() > 1) in = in.substring(1);
        this.integer = in;
        this.decimal = decimal;
        this.isPositive = p;
    }
    public HugeNumber(String str) {
        if (str.charAt(0) == '-') {
            this.isPositive = false;
            if (!str.contains(".")) {
                String inte = str.substring(1);
                while(inte.charAt(0) == '0' && inte.length() > 1) inte = inte.substring(1);
                this.integer = inte;
                this.decimal = "0";
            }
            else {
                int t = str.indexOf(".");
                String inte = str.substring(1, t);
                while(inte.charAt(0) == '0' && inte.length() > 1) inte = inte.substring(1);
                this.integer = inte;
                this.decimal = str.substring(t + 1);
            }
        }
        else if (str.charAt(0) == '+') {
            this.isPositive = true;
            if (!str.contains(".")) {
                String inte = str.substring(1);
                while(inte.charAt(0) == '0' && inte.length() > 1) inte = inte.substring(1);
                this.integer = inte;
                this.decimal = "0";
            }
            else {
                int t = str.indexOf(".");
                String inte = str.substring(1, t);
                while(inte.charAt(0) == '0' && inte.length() > 1) inte = inte.substring(1);
                this.integer = inte;
                this.decimal = str.substring(t + 1);
            }
        }
        else {
            this.isPositive = true;
            if (!str.contains(".")) {
                String inte = str;
                while(inte.charAt(0) == '0' && inte.length() > 1) inte = inte.substring(1);
                this.integer = inte;
                this.decimal = "0";
            }
            else {
                int t = str.indexOf(".");
                String inte = str.substring(0, t);
                while(inte.charAt(0) == '0' && inte.length() > 1) inte = inte.substring(1);
                this.integer = inte;
                this.decimal = str.substring(t + 1);
            }
        }
    }

    public String getInteger(){
        return this.integer;
    }
    public String getDecimal(){
        return this.decimal;
    }
    public boolean isPos() {return this.isPositive;}

    public int compareInteger(String a, String b){
//        if(this.isPos() && !a.isPos()) return 1;
//        if(!this.isPos() && a.isPos()) return -1;
//        if(this.isPos() && a.isPos()){
//            int len1 = this.getIntBit(), len2 = a.getIntBit(), len3 = this.getDecBit(), len4 = a.getDecBit();
//            if(len1 > len2)  return 1;
//            else if(len1 < len2) return -1;
//            else{
//                String i1 = this.getInteger();
//                String i2 = a.getInteger();
//                String d1 = this.getDecimal();
//                String d2 = a.getDecimal();
//                for(int i = 0; i < len1; ++ i){
//                    if ((int)(i1.charAt(i)) > (int)(i2.charAt(i))) return 1;
//                    if ((int)(i1.charAt(i)) < (int)(i2.charAt(i))) return -1;
//                }
//                if(len3 < len4) {
//                    for(int i = 0; i < len3; ++ i){
//                        if ((int)(d1.charAt(i)) > (int)(d2.charAt(i))) return 1;
//                        if ((int)(d1.charAt(i)) < (int)(d2.charAt(i))) return -1;
//                    }
//                }
//
//            }
//        }
        int len1 = a.length(), len2 = b.length();
        if(len1 > len2) return 1;
        if(len1 < len2) return -1;
        for(int i = 0; i < len1; ++ i) {
            if ((int)(a.charAt(i)) > (int)(b.charAt(i))) return 1;
            if ((int)(a.charAt(i)) < (int)(b.charAt(i))) return -1;
        }
        return 0;
    }                   //比较2个整数的大小

    public int compareDecimal(String a, String b){
        int len1 = a.length(), len2 = b.length();
        int i;
        for(i = 0; i < len1 && i < len2; ++ i){
            if ((int)(a.charAt(i)) > (int)(b.charAt(i))) return 1;
            if ((int)(a.charAt(i)) < (int)(b.charAt(i))) return -1;
        }
        if(i == len1 && i < len2){
            while (i < len2 - 1 && b.charAt(i) == '0') ++ i;
            if(b.charAt(i) > '0') return -1;
        }
        if(i == len2 && i < len1){
            while (i < len1 - 1 && a.charAt(i) == '0') ++ i;
            if(a.charAt(i) > '0') return 1;
        }
        return 0;
    }                   //比较2个小数的大小

    public int compareAbs(HugeNumber a, HugeNumber b){
        if(compareInteger(a.getInteger(), b.getInteger()) == 1) return 1;
        if(compareInteger(a.getInteger(), b.getInteger()) == -1) return -1;

//        System.out.println(a.getDecimal());
//        System.out.println(b.getDecimal());

        return compareDecimal(a.getDecimal(), b.getDecimal());
    }               //比较2个HugeNuber绝对值的大小

    public HugeNumber inverseHugeNumber(){
        return new HugeNumber(this.getInteger(), this.getDecimal(), !this.isPos());
    }                           //取相反数

    public String addCoreInteger(String a, String b, int[] c){
        c[0] = 0;
        String res = "";
        int u = a.length(), v = b.length();
        if(u != v){
            String t = "";
            for(int i = 0; i < Math.abs(u-v); ++ i) t += "0";
            if(u > v) b = t + b;
            else a = t + a;
        }
        int len = a.length();
        for(int i = len - 1; i >= 0; -- i){
            int tmp = Integer.parseInt(String.valueOf(a.charAt(i))) + Integer.parseInt(String.valueOf(b.charAt(i))) + c[0];
            if(tmp > 9){
                c[0] = 1;
                tmp -= 10;
            }
            else c[0] = 0;
            res = String.valueOf(tmp) + res;
        }
        while(res.charAt(0) == '0' && res.length() > 1 && c[0] == 0) res = res.substring(1);
        return res;
    }       //整数加法主要算法

    public String addPosInteger(String a, String b){
        int[] c = {0};
        String res = addCoreInteger(a,b,c);
        if(c[0] == 1) {res = "1" + res; c[0] = 0;}
        return res;
    }                 //正整数加法

    public String addCoreDecimal(String a, String b, int[] d){
        d[0] = 0;
        String res = "";
        int u = a.length(), v = b.length();
        if(u != v){
            String t = "";
            for(int i = 0; i < Math.abs(u-v); ++ i) t += "0";
            if(u > v) b = b + t;
            else a = a + t;
        }
        int len = a.length();
        for(int i = len - 1; i >= 0; -- i){
            int tmp = Integer.parseInt(String.valueOf(a.charAt(i))) + Integer.parseInt(String.valueOf(b.charAt(i))) + d[0];

//            System.out.println(tmp);

            if(tmp > 9){
                d[0] = 1;
                tmp -= 10;
            }
            else d[0] = 0;
            res = String.valueOf(tmp) + res;
        }
        return res;
    }       //小数加法主要算法

    public String subCoreInteger(String a, String b, int[] e){
        e[0] = 0;
        String res = "";
        int u = a.length(), v = b.length();
        if(u != v){
            String t = "";
            for(int i = 0; i < Math.abs(u-v); ++ i) t += "0";
            if(u > v) b = t + b;
            else a = t + a;
        }
        int len = a.length();
        for(int i = len - 1; i >= 0; -- i){
            int tmp = Integer.parseInt(String.valueOf(a.charAt(i))) - Integer.parseInt(String.valueOf(b.charAt(i))) - e[0];
            if(tmp < 0){
                e[0] = 1;
                tmp += 10;
            }
            else e[0] = 0;
            res = String.valueOf(tmp) + res;
        }
        while(res.charAt(0) == '0' && res.length() > 1 && e[0] == 0) res = res.substring(1);
        return res;
    }       //整数减法主要算法

    public String subPosInteger(String a, String b){
        int[] e = {0};
        String res = subCoreInteger(a, b, e);
        e[0] = 0;
        return res;
    }                 //正整数减法（大减小）

    public String subCoreDecimal(String a, String b, int[] f){
        f[0] = 0;
        String res = "";
        int u = a.length(), v = b.length();
        if(u != v){
            String t = "";
            for(int i = 0; i < Math.abs(u-v); ++ i) t += "0";
            if(u > v) b = b + t;
            else a = a + t;
        }
        int len = a.length();
        for(int i = len - 1; i >= 0; -- i){
            int tmp = Integer.parseInt(String.valueOf(a.charAt(i))) - Integer.parseInt(String.valueOf(b.charAt(i))) - f[0];
            if(tmp < 0){
                f[0] = 1;
                tmp += 10;
            }
            else f[0] = 0;
            res = String.valueOf(tmp) + res;
        }
        return res;
        }       //小数减法主要算法

    public String multiplyCore(String a, char b){
        int f2 = Integer.parseInt(String.valueOf(b));
        int[] f1 = new int[a.length()];
        for(int i = a.length() - 1; i >= 0; -- i)  f1[a.length() - 1 - i] = Integer.parseInt(String.valueOf(a.charAt(i)));
        int c = 0;
        String res = "";
        for(int i = 0; i < a.length(); ++ i){
            int tmp = f2 * f1[i] + c;
            c = 0;
            if(tmp > 9) {c = tmp / 10; tmp %= 10;}
            res = String.valueOf(tmp) + res;
        }
        if(c != 0) res = String.valueOf(c) + res;
        return res;
    }                    //乘法主要算法

    public int divOneBit(String a, String b){           //a比b高1位，求一位商
        int i = 0;
        String tmp = "0";
        while(compareInteger(a, tmp) == 1){
            ++ i;
            if(i == 10) return 9;
            tmp = multiplyCore(b, (char)(i + 48));
        }
        if(compareInteger(a, tmp) == 0) return i;
        else return i - 1;
    }                        //求一位商

    public int isPowerOfTen(){
        String t = integer + decimal;
        int len = t.length(), oneOccurTimes = 0;
        for(int i = 2; i < 10; ++ i)
            if(t.contains(String.valueOf(i))) return 0;
        for(int i = 0; i < len; ++ i)
            if(t.charAt(i) == '1') ++ oneOccurTimes;
        if(oneOccurTimes != 1) return 0;
        else{
            if(integer.contains("1")) {
                int mi = integer.length() - integer.indexOf("1") - 1;
                if(mi == 0) return 9999;
                else return mi;
            }
            else return - decimal.indexOf("1") -1;
        }
    }                                       //判断是否是10的整数幂次

    public HugeNumber getReciprocal(int bitNum){
        if(this.isPowerOfTen() == 9999) return new HugeNumber("1");
        else if(this.isPowerOfTen() != 0) {
            int t = this.isPowerOfTen();
            String tmp = "";
            if(t > 0) {
                while(t - 1 > 0){
                    tmp += "0";
                    -- t;
                }
                tmp += "1";
                return new HugeNumber("0", tmp, this.isPos());
            }
            else{
                while (t < 0){
                    tmp += "0";
                    ++ t;
                }
                tmp = "1" + tmp;
                return new HugeNumber(tmp, "0", this.isPos());
            }
        }
        else {
            int len1 = integer.length(), len2 = decimal.length(), len = len1 + len2;
            String dividend = "1", divider = integer + decimal, res = "";
            while(divider.charAt(0) == '0' && divider.length() > 1) divider = divider.substring(1);
            for(int i = 0; i < divider.length(); ++ i) dividend += "0";
            int resBit;
//            System.out.println();
//            System.out.println("dividend:" + dividend);
//            System.out.println("divider:" + divider);
//            System.out.println();
            for(int i = 0; i < bitNum; ++ i){
                resBit = divOneBit(dividend, divider);
//                System.out.println(resBit);
                res += String.valueOf(resBit);
                dividend = subPosInteger(dividend, multiplyCore(divider, (char)(resBit + 48))) + "0";
                while(dividend.charAt(0) == '0' && dividend.length() > 1) dividend = dividend.substring(1); //把当前被除数前面多余的0去掉
//                if(dividend.equals("0")) break;
//                System.out.println(dividend);
            }

            String inte, deci;
            if(!Objects.equals(integer, "0")){
                inte = "0";
                deci = res;
                for(int i = 0; i < len1 - 1; ++ i) deci = "0" + deci;
                return new HugeNumber(inte, deci, this.isPos());
            }
            else{
                int p = 0;              // 记录decimal最开头有几个0
                while(decimal.charAt(p) == '0') ++ p;
                inte = res.substring(0, p + 1);
                deci = res.substring(p + 1);
                return new HugeNumber(inte, deci, this.isPos());
            }
        }
    }                     //求倒数，bitNum为指定有效数字个数

    public HugeNumber jia(HugeNumber b){
        String t1 = this.getInteger(), t2 = b.getInteger(), t3 = this.getDecimal(), t4 = b.getDecimal();

        if(this.isPos() && b.isPos()){
            int[] d = {0}, c = {0};
            String res2 = addCoreDecimal(t3, t4, d);
            String res1 = addCoreInteger(t1, t2, c);
            if(c[0] == 1) {res1 = "1" + res1; c[0] = 0;}
            if(d[0] == 1) res1 = addCoreInteger(res1, "1", c);
            if(c[0] == 1) {res1 = "1" + res1; c[0] = 0;}

            return new HugeNumber(res1, res2, true);
        }
        else if(!this.isPos() && !b.isPos()){
            int[] c = {0}, d = {0};
            String res2 = addCoreDecimal(t3, t4, d);
            String res1 = addCoreInteger(t1, t2, c);
            if(c[0] == 1) {res1 = "1" + res1; c[0] = 0;}
            if(d[0] == 1) res1 = addCoreInteger(res1, "1", c);
            if(c[0] == 1) {res1 = "1" + res1; c[0] = 0;}
            return new HugeNumber(res1, res2, false);
        }
        else{
            if(compareAbs(this, b) == 0) return new HugeNumber();
            else if(compareAbs(this, b) == 1){
                int[] e = {0}, f = {0};
                String res2 = subCoreDecimal(t3, t4, f);
                String res1 = subCoreInteger(t1, t2, e);
                if(f[0] == 1) res1 = subCoreInteger(res1, "1", e);
                if(this.isPos() && !b.isPos()) return new HugeNumber(res1, res2, true);
                else return new HugeNumber(res1, res2, false);
            }
            else {
                int[] e = {0}, f = {0};
                String res2 = subCoreDecimal(t4, t3, f);
                String res1 = subCoreInteger(t2, t1, e);
                if(f[0] == 1) res1 = subCoreInteger(res1, "1", e);
                if(this.isPos() && !b.isPos()) return new HugeNumber(res1, res2, false);
                else return new HugeNumber(res1, res2, true);
            }
        }
    }                             //大数加大数

    public HugeNumber jian(HugeNumber b) {   return this.jia(b.inverseHugeNumber());}   //大数减大数

    public HugeNumber cheng(HugeNumber b){
        String t1 = this.getInteger(), t2 = b.getInteger(), t3 = this.getDecimal(), t4 = b.getDecimal();
        String f1 = t1 + t3;
        String f2 = t2 + t4;
//        while(f1.charAt(0) == '0' && f1.length() > 0) f1 = f1.substring(1);
//        while(f2.charAt(0) == '0' && f2.length() > 0) f2 = f2.substring(1);
        int deciLen = t3.length() + t4.length();
        String res = "0";
        String buZeros = "";
        for(int i = f2.length() - 1; i >= 0; -- i){
            String tmp = multiplyCore(f1, f2.charAt(i)) + buZeros;
            buZeros += "0";
            res = addPosInteger(tmp, res);
        }
        int inteLen = res.length() - deciLen;
        String inte, deci;
        if(inteLen <= 0) inte = "0";
        else inte = res.substring(0,inteLen);
        if(inteLen < 0) {
            String buDecZero = "";
            for(int i = inteLen; i < 0; ++ i) buDecZero += "0";
            deci = buDecZero + res;
        }
        else deci = res.substring(inteLen);

//
//        System.out.println();
//        System.out.println(inte);
//        System.out.println(deci);
//        System.out.println();

        if((this.isPos() && b.isPos()) || (!this.isPos() && !b.isPos())) return new HugeNumber(inte, deci, true);
        else return new HugeNumber(inte, deci, false);
    }                           //大数乘大数

    public HugeNumber chengMi(int n){
        if(n == 0) return new HugeNumber("1");
        else if(n == 1) return this;
        else{
            HugeNumber a = this;
            while(n > 1) {
                a = a.cheng(this);
                -- n;
            }
            return a;
        }
    }                                //大数乘幂


    public HugeNumber lnTaylor(int n){                                  //taylor展开求lnx，精确到小数点后n位
        HugeNumber one = new HugeNumber("1");
        if(compareAbs(this, one) == 0) return new HugeNumber();
        else{                                                       // 因为x输入范围[1,100],所以x>=1; else的情况为x>1

            HugeNumber cmp, cmpr, addTerm;

            cmp = new HugeNumber("2");
            cmpr = new HugeNumber("0.5");
            addTerm = new HugeNumber("0.693147180559945309417232121458176568075500134360255");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnTaylor(n));

            cmp = new HugeNumber("1.25");
            cmpr = new HugeNumber("0.8");
            addTerm = new HugeNumber("0.2231435513142097557662950903098345033746010855480");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnTaylor(n));

            cmp = new HugeNumber("1.024");
            cmpr = new HugeNumber("0.9765625");
            addTerm = new HugeNumber("0.02371652661731604211834685052867305795169687771623");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnTaylor(n));

            cmp = new HugeNumber("1.01");
            cmpr = new HugeNumber("0.99009900990099009900990099009900990099009900990099");
            addTerm = new HugeNumber("0.009950330853168082848215357544260741688679609940");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnTaylor(n));

            cmp = new HugeNumber("1.001");
            cmpr = new HugeNumber("0.999000999000999000999000999000999000999000999000999");
            addTerm = new HugeNumber("0.000999500333083533166809398920535011460755062393");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnTaylor(n));

            cmp = new HugeNumber("1.0001");
            cmpr = new HugeNumber("0.9999000099990000999900009999000099990000999900009999");
            addTerm = new HugeNumber("0.000099995000333308335333166680951131063482064401071");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnTaylor(n));


            String jbn = "0.";
            for(int i = 0; i < n; ++ i) jbn += "0";
            jbn += "1";
            HugeNumber jingdu = new HugeNumber(jbn);

            HugeNumber xo = one.jian(this.getReciprocal(31));        //ln(t) = - ln[1 - (1 - 1/t)], xo = 1 - 1/t > 0
            HugeNumber term = xo, res = new HugeNumber(), tmp;
            int i = 2;
            while(compareAbs(term, jingdu) == 1){                   //这一步确定了精确步数，仅当加的项小于“jingdu”时，才停止继续加
                res = res.jia(term);
                tmp = new HugeNumber(String.valueOf(i));
                tmp = tmp.getReciprocal(31);                        // 1/i
                term = xo.chengMi(i).cheng(tmp);                    //(xo^i) / i
                ++ i;
            }
            return res;                                             // - ln(1 - x) = - (- (x + x^2 / 2 + x^3 / 3 + ...)) = x + x^2 / 2 + x^3 / 3 + ...

        }
    }

        public HugeNumber lnIntegration(int n){                             //数值积分求lnx，用复化辛普森公式，分为n段小区间来算
        HugeNumber one = new HugeNumber("1");
        if(compareAbs(this, one) == 0) return new HugeNumber();
        else {
            HugeNumber cmp, cmpr, addTerm;

            cmp = new HugeNumber("2");
            cmpr = new HugeNumber("0.5");
            addTerm = new HugeNumber("0.693147180559945309417232121458176568075500134360255");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnIntegration(n));

            cmp = new HugeNumber("1.25");
            cmpr = new HugeNumber("0.8");
            addTerm = new HugeNumber("0.2231435513142097557662950903098345033746010855480");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnIntegration(n));

            cmp = new HugeNumber("1.024");
            cmpr = new HugeNumber("0.9765625");
            addTerm = new HugeNumber("0.02371652661731604211834685052867305795169687771623");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnIntegration(n));

            cmp = new HugeNumber("1.01");
            cmpr = new HugeNumber("0.99009900990099009900990099009900990099009900990099");
            addTerm = new HugeNumber("0.009950330853168082848215357544260741688679609940");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnIntegration(n));

            cmp = new HugeNumber("1.001");
            cmpr = new HugeNumber("0.999000999000999000999000999000999000999000999000999");
            addTerm = new HugeNumber("0.000999500333083533166809398920535011460755062393");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnIntegration(n));

            cmp = new HugeNumber("1.0001");
            cmpr = new HugeNumber("0.9999000099990000999900009999000099990000999900009999");
            addTerm = new HugeNumber("0.000099995000333308335333166680951131063482064401071");
            if(compareAbs(this, cmp) == 0) return addTerm;
            if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnIntegration(n));

            HugeNumber[] x = new HugeNumber[n + 1];
            HugeNumber nHuge = new HugeNumber(String.valueOf(n));
            HugeNumber nDao = nHuge.getReciprocal(31);
            HugeNumber dis = this.jian(one);
            HugeNumber h = nDao.cheng(dis);             //每个区间的长度
            for(int i = 0; i <= n; ++ i){
                HugeNumber jbn = new HugeNumber(String.valueOf(i));
                x[i] = jbn.cheng(h).jia(one);
            }

            HugeNumber two = new HugeNumber("2");
            HugeNumber four = new HugeNumber("4");
            HugeNumber oneInSix = new HugeNumber("6").getReciprocal(31);
            HugeNumber half = new HugeNumber("0.5");
            HugeNumber sum = x[0].getReciprocal(31).jia(x[n].getReciprocal(31));

            HugeNumber jbn = new HugeNumber();
            for(int i = 1; i < n; ++ i) jbn = jbn.jia(x[i].getReciprocal(31));

            sum = sum.jia(jbn.cheng(two));
//            sum.display();
//            System.out.println();

            HugeNumber tmp;
            HugeNumber jbnn = new HugeNumber();
            for(int i = 0; i < n; ++ i) {
                tmp = h.cheng(half);
                tmp = x[i].jia(tmp);
                tmp = tmp.getReciprocal(31);
                jbnn = jbnn.jia(tmp);
//                tmp = tmp.cheng(four);
//                sum = sum.jia(tmp);
//                tmp.display();
//                sum.display();
//                System.out.println();
            }
            sum = sum.jia(jbnn.cheng(four));


            HugeNumber res = sum.cheng(oneInSix).cheng(h);
            return res;
        }
    }

    public HugeNumber lnSeriesFraction(int n){                          //非taylor展开的函数逼近方法求lnx，这里为用连分数展开，最后一个分母的常数项取到n
        HugeNumber one = new HugeNumber("1");
        if(compareAbs(this, one) == 0) return new HugeNumber();
        HugeNumber x = this.jian(one);

        HugeNumber cmp, cmpr, addTerm;

        cmp = new HugeNumber("2");
        cmpr = new HugeNumber("0.5");
        addTerm = new HugeNumber("0.693147180559945309417232121458176568075500134360255195");
        if(compareAbs(this, cmp) == 0) return addTerm;
        if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnSeriesFraction(n));

        cmp = new HugeNumber("1.25");
        cmpr = new HugeNumber("0.8");
        addTerm = new HugeNumber("0.22314355131420975576629509030983450337460108554800720857");
        if(compareAbs(this, cmp) == 0) return addTerm;
        if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnSeriesFraction(n));

        cmp = new HugeNumber("1.024");
        cmpr = new HugeNumber("0.9765625");
        addTerm = new HugeNumber("0.0237165266173160421183468505286730579516968777162336131");
        if(compareAbs(this, cmp) == 0) return addTerm;
        if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnSeriesFraction(n));

        cmp = new HugeNumber("1.01");
        cmpr = new HugeNumber("0.99009900990099009900990099009900990099009900990099");
        addTerm = new HugeNumber("0.009950330853168082848215357544260741688679609940");
        if(compareAbs(this, cmp) == 0) return addTerm;
        if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnSeriesFraction(n));

        cmp = new HugeNumber("1.001");
        cmpr = new HugeNumber("0.999000999000999000999000999000999000999000999000999");
        addTerm = new HugeNumber("0.000999500333083533166809398920535011460755062393");
        if(compareAbs(this, cmp) == 0) return addTerm;
        if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnSeriesFraction(n));

        cmp = new HugeNumber("1.0001");
        cmpr = new HugeNumber("0.9999000099990000999900009999000099990000999900009999");
        addTerm = new HugeNumber("0.000099995000333308335333166680951131063482064401071");
        if(compareAbs(this, cmp) == 0) return addTerm;
        if(compareAbs(this, cmp) == 1) return addTerm.jia(this.cheng(cmpr).lnSeriesFraction(n));

        HugeNumber[] num = new HugeNumber[n+1];
        HugeNumber res;
        for(int i = 0; i <= n; ++ i) num[i] = new HugeNumber(String.valueOf(i));
        HugeNumber lastNum = num[n].jian(num[n-1].cheng(x));
        for(int i = n - 1; i > 0; -- i){
            HugeNumber tmp1 = lastNum.getReciprocal(31);
            tmp1 = num[i].cheng(num[i]).cheng(x).cheng(tmp1);
            HugeNumber tmp2 = num[i-1].cheng(x);
            lastNum = num[i].jian(tmp2).jia(tmp1);
        }
        res = x.cheng(lastNum.getReciprocal(31));
        return res;
    }

    public HugeNumber getFirstNBits(int n){                             //截断，精确到小数点后n位
        if(this.getDecimal().length() <= n) return this;
        else{
            int nThBit = Integer.parseInt(String.valueOf(this.getDecimal().charAt(n)));
            String deci;
            if(nThBit >= 0 && nThBit < 5) {
                deci = this.getDecimal().substring(0,n);
                return new HugeNumber(this.getInteger(), deci, this.isPos());
            }
            else{
                deci = this.getDecimal().substring(0,n);
                String tmp = "0.";
                for(int i = 0; i < n - 1; ++ i) tmp += "0";
                tmp += "1";
                HugeNumber t = new HugeNumber(tmp);
                HugeNumber ori = new HugeNumber(this.getInteger(),deci, this.isPos());
                if(this.isPos()) return ori.jia(t);
                else return ori.jian(t);
            }
        }
    }

    public String display(){                                          //显示结果
        if(isPos()) return integer + "." + decimal;
        else return "-" + integer + "." + decimal;
    }

}