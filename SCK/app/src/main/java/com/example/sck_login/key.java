package com.example.sck_login;

class key {
    public String keyword, description;

    public key() { }

    public key(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
    }

    void setKeyword(String keyword){
        this.keyword = keyword;
    }

    void setDescription(String description){
        this.description = description;
    }

    public String getKeyword(){
        return keyword;
    }

    public String getDescription(){
        return description;
    }

}
