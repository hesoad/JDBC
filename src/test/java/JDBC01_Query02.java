import java.sql.*;

import static java.sql.DriverManager.getConnection;



public class JDBC01_Query02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();

        /*=======================================================================
       	 ORNEK1: Id'si 1'den buyuk firmalarin ismini ve iletisim_isim'ini isim
       	 ters sirali yazdirin.
      	========================================================================*/

        /*
        CREATE TABLE firmalar
        (
        id INT,
        isim VARCHAR(20),
        iletisim_isim VARCHAR(20),
        CONSTRAINT firmalar_pk PRIMARY KEY (id, isim)
        );

        INSERT INTO firmalar VALUES
        (1, 'ACB', 'Ali Can'),
        (2, 'RDB', 'Veli Gul'),
        (3, 'KMN', 'Ayse Gulmez');
         */

        String selectquery = "SELECT isim, iletisim_isim " +
                "FROM firmalar " +
                "WHERE id>1 " +
                "ORDER BY isim DESC";

        String selectquery2 = "SELECT isim, iletisim_isim FROM firmalar WHERE id>1 ORDER BY isim DESC";

        ResultSet data2 = st.executeQuery(selectquery);

        while (data2.next()) {
            System.out.println(data2.getString("isim") + " " +
                    data2.getString("iletisim_isim"));
        }

        System.out.println("=======================================ORNEK 2===================================================");
        /*=======================================================================
         ORNEK2: Iletisim isminde 'li' iceren firmalarin id'lerini ve isim'ini
          id sirali yazdirin.
        ========================================================================*/

        String selectquery3= "Select id, isim from firmalar where iletisim_isim like '%li%' order by id";

        data2 = st.executeQuery(selectquery3);

        while (data2.next()) {
            System.out.println(data2.getInt("id") + " " +
                    data2.getString("isim"));
        }
        // NOT1 : Sorgulama icin get ile istenirse sütun (field) ismini yazabilecegimiz gibi sutun index
        // (field olusturulma sirasina gore) yazilabilir.

        // NOT2 : Sorgumuzda SELECT'ten sonra sadece belli fieldlari dondurmesini istiyorsak
        // get ile cagirdigimiz field indexleri sorguda belirttigimiz sirayla ifade etmemiz gerekiyor

        while (data2.next()) {
            System.out.println(data2.getInt(2) + " " +
                    data2.getString(1));
        }
        con.close();
        st.close();
        data2.close();



    }
}