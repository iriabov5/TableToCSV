//package org.example;
//
//import au.com.bytecode.opencsv.CSVWriter;
//
//import java.io.FileWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class App {
//    public static void main(String[] args) {
//        // args[0] - это имя базы данных
//        // args[1] - это имя таблицы
//
//        String url = "jdbc:postgresql://localhost:5432/" + args[0];
//        String sql = "SELECT * FROM " + args[1] + ";";
//        Connection c;
//        Statement stmt;
//        try {
//            Class.forName("org.postgresql.Driver");
//            c = DriverManager
//                    .getConnection(url, "postgres", "postgres");
//            c.setAutoCommit(false);
//            stmt = c.createStatement();
//            String csv = "/Users/ivan/Desktop/data.csv";
//            CSVWriter writer = new CSVWriter(new FileWriter(csv, false));
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                int columnCount = rs.getMetaData().getColumnCount();
//                String[] strings = new String[columnCount];
//                for (int i = 0; i < columnCount; i++) strings[i] = rs.getString(i + 1);
//                writer.writeNext(strings);
//            }
//            writer.close();
//            rs.close();
//            stmt.close();
//            c.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//    }
//}
//
