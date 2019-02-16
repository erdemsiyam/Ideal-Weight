package com.idealkilo.siyam.ideal_kilo;


import static java.lang.Math.pow;

public class İnsan {
    private double kilo; // orn : 75 kg
    private double boy; // orn : 1.75 m
    private boolean erkek; // evet ise 1 , değil : kızsa 0
    private String durum ; // kişinin kilo durumu açıklaması

    public static final  double  INC_DEGERI =0.3937007874; // 1 inch = 0.39 cm'dir.



    public İnsan(double kilo, double boy, boolean erkek) {
        this.kilo = kilo;
        this.boy = boy;
        this.erkek = erkek;
    }

    public static double vucutKitleIndexHesap(İnsan i) {
        // idealKilo = kilo / boy^2
        double boy = i.getBoy();
        double index = i.getKilo()/(pow(boy,2));

        if(index<18.5)
            i.setDurum("zayıf");
        else if(index <25)
            i.setDurum("Normal Kilo");
        else if (index <30)
            i.setDurum("Fazla Kilolu");
        else if (index <35)
            i.setDurum("I.Derece obez");
        else if (index <40)
            i.setDurum("II.Derece obez");
        else
            i.setDurum("III.Derece morbid obez");

        return index;
    }

    public static double idealKiloHesap(İnsan i) {   /*
            Kadınlarda ideal kilo: 45,5 + 2,3 X (İnç cinsinden boy - 60)
            Erkeklerde ideal kilo: 50 + 2,3 X (İnç cinsinden boy - 60)
        */
        double yeniBoy = i.getBoy()*100; // 1.75 gibi virgüllü boyu 3 basamaklı 175 hale çeviririz ki ideal kiloyu aşağıda bulabilmek için
        if (i.isErkek())
            return (50 + (2.3 * ((INC_DEGERI*yeniBoy) - 60)));
            return (45.5 + (2.3 * ((INC_DEGERI*yeniBoy) - 60)));
    }

    public static double yağsızKiloHesapla(İnsan i) {

        /*
            Erkek : (1.10 x Ağırlık (kg)) - 128 (Ağırlık ^ 2 / (100 x Boy(m)) ^2)
            Bayan : (1.07 x Ağırlık (kg)) - 148 (Ağırlık2/(100 x Boy(m))2)
        */
        double boy = i.getBoy()*100;
        double kilo = i.getKilo();
        if (i.isErkek())
            return (1.10*kilo)-(128*(pow(kilo,2)/(pow(boy,2))));
            return (1.07*kilo)-148*(pow(kilo,2)/(pow(boy,2)));

    }

    public static double vücutYüzeyAlanı(İnsan i) {
        /*
        Vücut Yüzey Alanı : 0.20247 x Boy (m)0.725 x Ağırlık (kg)0.425
        */
        return 0.20247*pow(i.getBoy(),0.725)*pow(i.getKilo(),0.425);
    }


    //***** GETTER AND SETTERS *******
    public boolean isErkek() {
        return erkek;
    }
    public void setErkek(boolean erkek) {
        this.erkek = erkek;
    }
    public double getKilo() {
        return kilo;
    }
    public void setKilo(double kilo) {
        this.kilo = kilo;
    }
    public double getBoy() {
        return boy;
    }
    public void setBoy(double boy) {
        this.boy = boy;
    }
    public String getDurum() {
        return durum;
    }
    public void setDurum(String durum) {
        this.durum = durum;
    }

}
