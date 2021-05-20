package mhl.utils;

import java.sql.Connection;

public class Test {
    public static void main(String[] args)throws Exception {
//        int a = Utility.readInt();
//        System.out.println(a);
        String s = Utility.readString(1);
        System.out.println(s);
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
        JDBCUtilsByDruid.close(null,null,connection);
    }
}
