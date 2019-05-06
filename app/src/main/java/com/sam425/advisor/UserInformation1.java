package com.sam425.advisor;

public class UserInformation1 {

    public String date;
    public String food;
    public String health;
    public String transport;
    public String grocery;
    public String rent;
    public String other;
    public String overall;
    public String amount;
    public String time;
    public String merchant;
    public String category;
    public String pname;
    public String pprice;

    public UserInformation1()
    {

    }

    public UserInformation1(String date, String time,String amount,String merchant, String category , String pname,String pprice) {
        this.amount = amount;
        this.date = date;
        this.time=time;
        this.merchant=merchant;
        this.category=category;
        this.pname=pname;
        this.pprice=pprice;

    }

    public UserInformation1(String date1,String food1,String health1,String transport1,String grocery1,String rent1,String other1,String overall1) {

        this.date = date1;
        this.food=food1;
        this.health=health1;
        this.transport=transport1;
        this.grocery=grocery1;
        this.rent=rent1;
        this.other=other1;
        this.overall=overall1;

    }

    public String usedate(){return date;}
    public String usefood(){return food;}
    public String usehealth(){return health;}
    public String usetransport(){return transport;}
    public String usegrocery(){return grocery;}
    public String userent(){return rent;}
    public String useother(){return other;}
    public String useoverall(){return overall;}




}
