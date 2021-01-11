package org.example

import au.com.bytecode.opencsv.CSVWriter
import java.io.FileWriter
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        // args[0] - это имя базы данных
        // args[1] - это имя таблицы
        val url = "jdbc:postgresql://localhost:5432/" + args[0]
        val sql = "SELECT * FROM " + args[1] + ";"
        val c: Connection
        val stmt: Statement
        try {
            Class.forName("org.postgresql.Driver")
            c = DriverManager
                .getConnection(url, "postgres", "postgres")
            c.autoCommit = false
            stmt = c.createStatement()
            val csv = "/Users/ivan/Desktop/data.csv"
            val writer = CSVWriter(FileWriter(csv, false))
            val rs = stmt.executeQuery(sql)
            while (rs.next()) {
                val columnCount = rs.metaData.columnCount
                val strings = arrayOfNulls<String>(columnCount)
                for (i in 0 until columnCount) strings[i] = rs.getString(i + 1)
                writer.writeNext(strings)
            }
            writer.close()
            rs.close()
            stmt.close()
            c.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            System.err.println(e.javaClass.name + ": " + e.message)
            System.exit(0)
        }
    }
}