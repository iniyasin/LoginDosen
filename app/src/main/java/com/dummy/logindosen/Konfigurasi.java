package com.dummy.logindosen;

public class Konfigurasi {
    //URL untuk login kita, ip address bisa diganti sesuai dengan yang ada di perangkat masing-masing
    public static final String LOGIN_URL = "http://192.168.1.145/absen/login_dosen.php";

    //Key untuk nip dan password yang didefinisikan pada $_POST['key'] di login_dosen.php
    public static final String KEY_NIP = "nip";
    public static final String KEY_PASS = "password";

    //kalau respon si server sama dengan yang kita inputkan
    public static final String LOGIN_SUCCESS = "success";
}
