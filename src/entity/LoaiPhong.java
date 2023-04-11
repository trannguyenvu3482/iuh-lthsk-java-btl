package entity;

public class LoaiPhong {
    private String maLoai;
    private String tenLoai;
    private String chatLuong;

    public LoaiPhong(String maLoai, String tenLoai, String chatLuong) throws Exception {
        setMaLoai(maLoai);
        setTenLoai(tenLoai);
        setChatLuong(chatLuong);
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) throws Exception {
        if (maLoai.matches("L[0-9]{3}"))
            this.maLoai = maLoai;
        else
            throw new Exception("Mã loại phòng không hợp lệ (Pxxx với x là số)!");
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) throws Exception {
        if (tenLoai.equalsIgnoreCase("Phòng đơn") || tenLoai.equalsIgnoreCase("Phòng đôi"))
            this.tenLoai = tenLoai;
        else
            throw new Exception("Tên loại phòng không hợp lệ");
    }

    public String getChatLuong() {
        return chatLuong;
    }

    public void setChatLuong(String chatLuong) throws Exception {
        if (chatLuong.equalsIgnoreCase("Cao cấp") || chatLuong.equalsIgnoreCase("Thường"))
            this.chatLuong = chatLuong;
        else
            throw new Exception("Chất lượng phòng không hợp lệ");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
