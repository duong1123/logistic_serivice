package com.duongprj.logistic_service.enums;

public enum Region {
    HNI(new String[]{"Hà Nội", "Hải Phòng", "Lai Châu", "Lào Cai", "Hà Giang", "Cao Bằng", "Bắc Kạn",
            "Tuyên Quang", "Yên Bái", "Lạng Sơn", "Thái Nguyên", "Bắc Giang", "Quảng Ninh", "Hải Dương",
            "Hưng Yên", "Hà Nam", "Bắc Ninh", "Nam Định", "Ninh Bình", "Thái Bình", "Phú Thọ", "Vĩnh Phúc",
            "Sơn La", "Điện Biên", "Hòa Bình"}),

    HCM(new String[]{"Thành phố Hồ Chí Minh", "Cần Thơ", "Bình Phước", "Bình Dương", "Đồng Nai", "Tây Ninh",
            "Bà Rịa Vũng Tàu", "Long An", "Đồng Tháp", "Tiền Giang", "An Giang", "Bến Tre", "Vĩnh Long",
            "Trà Vinh", "Hậu Giang", "Kiên Giang", "Sóc Trăng", "Bạc Liêu", "Cà Mau"}),

    DAD(new String[]{"Thanh Hoá", "Nghệ An", "Hà Tĩnh", "Quảng Bình", "Quảng Trị", "Thừa Thiên Huế", "Đà Nẵng",
            "Quảng Nam", "Quảng Ngãi", "Bình Định", "Phú Yên", "Khánh Hòa", "Ninh Thuận", "Bình Thuận", "Kon Tum",
            "Gia Lai", "Đắk Lắk", "Đắk Nông", "Lâm Đồng"});

    private final String[] provinces;

    Region(String[] provinces) {
        this.provinces = provinces;
    }

    public String[] getProvinces() {
        return provinces;
    }

    public static Region getRegionByProvince(String province) {
        for (Region region : values()) {
            for (String prov : region.getProvinces()) {
                if (prov.equalsIgnoreCase(province)) {
                    return region;
                }
            }
        }
        throw new IllegalArgumentException("Province not found in any region");
    }
}
