package com.example.al.turinrally;

public class MainMenuListModel {

    private  String itemText="";
    private  String itemImage="";
     
    /*********** Set Methods ******************/
     
    public void setItemText(String itemText)
    {
        this.itemText = itemText;
    }
     
    public void setItemImage(String itemImage)
    {
        this.itemImage = itemImage;
    }
     
     
    /*********** Get Methods ****************/
     
    public String getItemText()
    {
        return this.itemText;
    }
     
    public String getItemImage()
    {
        return this.itemImage;
    }
 
 }
