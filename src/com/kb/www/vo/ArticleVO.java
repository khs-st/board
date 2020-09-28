package com.kb.www.vo;

public class ArticleVO {
    //글 번호
    private int ArticleNum;
    //작성자
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int mb_sq;

    public int getMb_sq() {
        return mb_sq;
    }

    public void setMb_sq(int mb_sq) {
        this.mb_sq = mb_sq;
    }

    //글 제목
    private String ArticleTitle;
    //글 내용
    private String ArticleContent;
    //조회수
    private int Hit;
    //작성일자
    private String writeDate;
    //수정일자
    private String updateDate;

    public int getArticleNum() {
        return ArticleNum;
    }

    public void setArticleNum(int articleNum) {
        ArticleNum = articleNum;
    }

    public String getArticleTitle() {
        return ArticleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        ArticleTitle = articleTitle;
    }

    public String getArticleContent() {
        return ArticleContent;
    }

    public void setArticleContent(String articleContent) {
        ArticleContent = articleContent;
    }

    public int getHit() {
        return Hit;
    }

    public void setHit(int hit) {
        Hit = hit;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    //삭제일자
    private String deleteDate;

}
