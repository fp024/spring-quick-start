package org.fp024.mybatistest.board.generated;

import java.time.LocalDateTime;

public class BoardVO {
    private Integer seq;

    private int cnt;

    private LocalDateTime regDate;

    private String content;

    private String title;

    private String writer;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", seq=").append(seq);
        sb.append(", cnt=").append(cnt);
        sb.append(", regDate=").append(regDate);
        sb.append(", content=").append(content);
        sb.append(", title=").append(title);
        sb.append(", writer=").append(writer);
        sb.append("]");
        return sb.toString();
    }
}