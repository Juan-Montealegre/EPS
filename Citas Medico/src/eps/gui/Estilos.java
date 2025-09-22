package eps.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

public class Estilos {

    // Paleta de colores azules de la EPS 🎨
    public static final Color AZUL_PRINCIPAL = new Color(0, 115, 183);    // Un azul corporativo
    public static final Color AZUL_OSCURO = new Color(10, 34, 64);      // Para texto y títulos
    public static final Color AZUL_CLARO = new Color(227, 242, 253);   // Para fondos
    public static final Color BLANCO = Color.WHITE;
    public static final Color GRIS_TEXTO = new Color(102, 102, 102);
    public static final Color VERDE_EXITO = new Color(34, 139, 34);

    // Fuentes ✒️
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font FUENTE_ETIQUETA = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 14);

    /**
     * Aplica el look and feel de Nimbus y personaliza los colores
     * para toda la aplicación.
     */
    public static void aplicar() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Si Nimbus no está disponible, se usará el L&F por defecto.
            e.printStackTrace();
        }
    }
}