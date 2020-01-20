package server;

public class configurl {
    public  static String baseUrl= "http://192.168.0.100:5000";
    public static String getadmin= baseUrl + "/admin/select";
    public static String posadmin=baseUrl+"/admin/insert";
    public static String loginadmin=baseUrl+"/admin/login";
    public static String inputcustomer=baseUrl+"/customer/insert";
    public  static  String  getcustomer=baseUrl+"/customer/select";
    public static String logincustomer=baseUrl+"/customer/login";
    public static String getmotor=baseUrl+"/motor/select";
    public static String logindulu=baseUrl+"/customer/logindulu";
   public static String pesaninput=baseUrl+"/pemesanan/insert";
    public static String updatepesan=baseUrl+"/pemesanan/update/";
   public static String deletepesan=baseUrl+"/pemesanan/delete/";
    public static String ambiilpesan=baseUrl+"/pemesanan/select";
}
