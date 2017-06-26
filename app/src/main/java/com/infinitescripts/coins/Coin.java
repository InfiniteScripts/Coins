package com.infinitescripts.coins;


public class Coin {
    private String type;
    private String year;
    private String purchaseSource;
    private String variety;
    private String salePrice;
    private String qr;
    private String purchasePrice;
    private String purchaseDate;
    private String privateComments;
    private String mementoID;
    private String holder;
    private String grade;
    private String publicComments;
    private String uid;
    private String imgUrl;
    private String soldPrice;


    public Coin(){

    }


    public String getGrade(){return this.grade;}
    public String getType(){return this.type;}
    public String getUid(){ return this.uid;}
    public String getQR(){return this.qr;}
    public String getImgUrl(){return this.imgUrl;}
    public String getHolder(){ return this.holder;}
    public String getYear() {return this.year; }
    public String getVariety(){ return this.variety;}
    public String getSalePrice() {  return this.salePrice;}
    public String getPurchasePrice(){return this.purchasePrice;}
    public String getPurchaseDate() {return this.purchaseDate;}
    public String getPurchaseSource(){return this.purchaseSource;}
    public String getPublicComments(){return this.publicComments;}
    public String getMementoID() { return this.mementoID;}
    public String getPrivateComments() {return this.privateComments;}
    public String getSoldPrice(){return this.soldPrice;}

    public void setGrade(String input){this.grade  = input;}
    public void setType(String input){this.type  = input;}
    public void setQR(String input){this.qr = input;}
    public void setHolder(String input){ this.holder = input;}
    public void setYear(String input) {this.year = input; }
    public void setVariety(String input){ this.variety = input;}
    public void setSalePrice(String input) { this.salePrice = input;}
    public void setPurchasePrice(String input){ this.purchasePrice = input;}
    public void setPurchaseDate(String input) {this.purchaseDate  = input;}
    public void setPurchaseSource(String input){this.purchaseSource  = input;}
    public void setPublicComments(String input){this.publicComments  = input;}
    public void setImgUrl(String input){this.imgUrl = input;}
    public void setMementoID(String input) { this.mementoID  = input;}
    public void setPrivateComments(String input) { this.privateComments  = input;}
    public void setUid(String input) { this.uid = input;}
    public void setSoldPrice(String input) { this.soldPrice = input;}
}
