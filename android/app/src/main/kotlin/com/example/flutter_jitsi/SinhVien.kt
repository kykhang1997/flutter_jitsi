package com.example.flutter_jitsi

class SinhVien(name: String, diachi: String, namsinh: Int) {
    public var name: String = ""
    public var diachi: String = ""
    private var namsinh: Int = 0

    init {
        this.name = name
        this.diachi = diachi
        if (namsinh > 1999) {
            this.namsinh = 1999
        } else {
            this.namsinh = namsinh
        }
    }

    fun getNamSinh(): Int {
        return namsinh
    }
}
