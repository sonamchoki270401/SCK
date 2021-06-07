package com.example.sck_login;

class key {
    public String keyword, description;
    int report;

    public key() { }


    public key(String keyword, String description, int report) {
        this.keyword = keyword;
        this.description = description;
    }

    void setKeyword(String keyword){
        this.keyword = keyword;
    }
    void setDescription(String description){ this.description = description; }
    public void setReport(int report) { this.report = report; }

    public int getReport() { return report; }
    public String getKeyword(){
        return keyword;
    }
    public String getDescription(){
        return description;
    }

}
