package app;

import app.controller.*;
import app.view.*;
import app.model.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.*;

@EnabledIfEnvironmentVariable(named="FORMULA_INTEGRATION_TESTS",matches="true")
public class SwingWorkflowTest {
    private static void edt(Runnable work) throws Exception { SwingUtilities.invokeAndWait(work); }
    @Test void openEveryRoleMenuAndEditProduction() throws Exception {
        Assumptions.assumeFalse(GraphicsEnvironment.isHeadless());
        DatabaseWorkflowTest fixture=new DatabaseWorkflowTest();fixture.fixture();
        int batch=fixture.batch();
        javax.swing.Timer dialogs=new javax.swing.Timer(100,e->{
            for(Window w:Window.getWindows()) if(w instanceof JDialog && w.isVisible()) w.dispose();
        });
        edt(dialogs::start);
        try {
            for(String role:new String[]{"Administrasi","Produksi","Fulfillment"}) {
                int user=role.equals("Administrasi")?fixture.admin:role.equals("Produksi")?fixture.produksi:fixture.fulfillment;
                AtomicReference<FormDashboard> dashboard=new AtomicReference<>();
                edt(()->{
                    FormDashboard d=new FormDashboard(user,role);new DashboardController(d,user,role);d.setVisible(true);dashboard.set(d);
                });
                int count=app.model.role.MenuRole.untuk(role).getMenu().length;
                for(int i=0;i<count;i++) {
                    int menu=i;edt(()->dashboard.get().getBtn(menu).doClick());
                    AtomicReference<JFrame> form=new AtomicReference<>();
                    edt(()->{
                        for(Window w:Window.getWindows()) if(w instanceof JFrame && w.isVisible() && w!=dashboard.get()) form.set((JFrame)w);
                    });
                    assertNotNull(form.get(),role+" menu "+i+" gagal dibuka");
                    if(form.get() instanceof FormMaster) {
                        FormMaster master=(FormMaster)form.get();
                        for(int attempt=0;attempt<150;attempt++) {
                            AtomicReference<Integer> rows=new AtomicReference<>();
                            edt(()->rows.set(master.getModel().getRowCount()));
                            if(rows.get()>0) break;
                            Thread.sleep(100);
                        }
                        if(role.equals("Produksi") && i==1) {
                            edt(()->{
                                master.setFieldValue(0,""+batch);master.setFieldValue(2,"123");
                                master.setFieldValue(3,"Botol");master.getBtnUpdate().doClick();
                            });
                            assertEquals(123,DatabaseWorkflowTest.angka("produksi","stok","nomor_batch",batch));
                            assertEquals("Botol",DatabaseWorkflowTest.teks("produksi","satuan_stok","nomor_batch",batch));
                        }
                    }
                    edt(()->form.get().dispatchEvent(new WindowEvent(form.get(),WindowEvent.WINDOW_CLOSING)));
                    edt(()->assertTrue(dashboard.get().isVisible()));
                }
                edt(()->dashboard.get().dispose());
            }
        } finally {
            edt(()->{dialogs.stop();for(Window w:Window.getWindows()) w.dispose();});
        }
    }
}
