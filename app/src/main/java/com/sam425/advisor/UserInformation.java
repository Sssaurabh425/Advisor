package com.sam425.advisor;

public class UserInformation {

    public String date;
    public String time;
    public String amount;
    public String merchant;
    public String category;
    public String id;
    public String userid;
    public String username;
    public String imageURL;
    public String panno;
    public String phoneNo;
    public String EmailId;
    public String uid;
    public String subname;
    public String subuid;
    public String subcat;
    public String imageurl1;
    public String returnper1;
    public String year1;
    public String fundname1;
    public String priority1;
    public String cap1;
    public String star1;
    public String fullname;
    public String mobno;
    public String subject;
    public String mesage;

    public UserInformation()
    {

    }


    public UserInformation(String panno, String phoneno, String username, String email, String uid, String userid){
        this.userid = userid;
        this.EmailId = email;
        this.username=username;
        this.panno=panno;
        this.phoneNo=phoneno;

        this.uid=uid;
    }
    public UserInformation(String imageurl,String returnper, String year,String fundname,String priority,String cap,String star)
    {
        this.imageurl1 = imageurl;
        this.returnper1= returnper;
        this.year1=year;
        this.fundname1=fundname;
        this.priority1=priority;

        this.cap1=cap;
        this.star1=star;
    }
    public UserInformation(String subname, String subuid, String subcat){
        this.subname=subname;
        this.subuid=subuid;
        this.subcat=subcat;
    }
    public UserInformation(String fullname1,String mobno1,String subject1,String message1)
    {
        this.fullname=fullname1;
        this.mobno=mobno1;
        this.subject=subject1;
        this.mesage=message1;
    }
    public UserInformation(String date, String time,String amount,String merchant, String category) {
        this.amount = amount;
        this.date = date;
        this.time=time;
        this.merchant=merchant;
        this.category=category;

    }
    public String usedate_time() {return date+"_"+time;}
    public String usedate(){return date;}
    public String usertime(){return time;}
    public String useramount(){return amount;}
    public String usermerchant(){return merchant;}
    public String usercategory(){return category;}
    public String userimageurl(){return imageURL;}
    public String userusn(){return username;}
    public String useremailid(){return EmailId;}
    public String userpanno(){return panno;}
    public String userphoneno(){return phoneNo;}
    public String userid(){return userid;}
    public String useruid(){return uid;}
    public String subname(){return subname;}
    public String subuid(){return subuid;}
    public String subcat(){return subcat;}
    public String usereturn(){return returnper1;}
    public String useryear(){return year1;}
    public String userfund(){return fundname1;}
    public String useprio(){return priority1;}
    public String usecap(){return cap1;}
    public String usestar(){return star1;}
    public String useimageurl1(){return imageurl1;}
public String useqid(){return id;}


}
