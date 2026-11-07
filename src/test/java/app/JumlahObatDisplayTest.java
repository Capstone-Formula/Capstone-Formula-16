package app;

import app.model.AlurDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JumlahObatDisplayTest {
    @Test
    void batchUtuhDitampilkanSebagaiJumlahObat() {
        assertEquals(25, AlurDAO.jumlahObatUntukTampilan(25));
        assertEquals(50, AlurDAO.jumlahObatUntukTampilan(50));
        assertEquals(100, AlurDAO.jumlahObatUntukTampilan(100));
    }

    @Test
    void dataLamaTetapMenampilkanNilaiTersimpan() {
        assertEquals(1, AlurDAO.jumlahObatUntukTampilan(1));
        assertEquals(null, AlurDAO.jumlahObatUntukTampilan(null));
        assertEquals(20, AlurDAO.jumlahObatUntukTampilan(20));
    }
}
