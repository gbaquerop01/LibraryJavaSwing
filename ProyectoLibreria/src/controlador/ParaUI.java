package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import modelo.Libro;
import utiles.Validaciones;
import vista.UI;

/**
 * Clase que controla todos los botones/lógica en la vista.
 */
public class ParaUI extends UI {

    public ParaUI(Libreria libreria) {
        rellenarTabla(libreria);
        ButtonGroup grupoFormato = addRadioButtonGroups(panelFormato);
        ButtonGroup grupoEdicion = addRadioButtonGroups(panelEdicion);

        btnGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String ISBN = iSBNTextField.getText();
                String título = tituloTextField.getText();
                String autor = autorTextField.getText();
                String editorial = editorialTextField.getText();
                String precioString = precioTextField.getText();
                String formato = getRadioSelect(grupoFormato);
                String edición = getRadioSelect(grupoEdicion);

                if (!Validaciones.validarISBN(ISBN) || !Validaciones.validarNombre(título)
                        || !Validaciones.validarNombre(autor) || !Validaciones.validarNombre(editorial)
                        || !Validaciones.validarPrecio(precioString)) {
                    JOptionPane.showMessageDialog(frame, "Campos Incorrectos", "Error", 0);
                    return;
                }
                if (libreria.existeISBN(ISBN)) {
                    JOptionPane.showMessageDialog(frame, "Ya existe ese ISBN", "ISBN Existente", 0);
                    return;
                }
                Float precio = Float.parseFloat(precioString);

                Libro libro = new Libro(ISBN, título, autor, editorial, precio, formato, edición);

                libreria.añadirLibro(libro);
                vaciarCampos();
                rellenarTabla(libreria);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnConsultar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String ISBNSelected = JOptionPane.showInputDialog(frame,
                        "Introduce el ISBN del libro que quieres consultar.", "Consulta de libro", 1);

                if (ISBNSelected == null || ISBNSelected.equals("") || !libreria.existeISBN(ISBNSelected)) {
                    JOptionPane.showMessageDialog(null, "ISBN incorrecto", "Error", 0);
                    return;
                }

                JOptionPane.showMessageDialog(null, libreria.buscarLibroPorISBN(ISBNSelected).toString(),
                        "Consulta de libro", 1);
            }

        });

        btnBorrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String ISBNSelected = JOptionPane.showInputDialog(frame,
                        "Introduce el ISBN del libro que quieres borrar.", "Borrar libro", 1);
                if (ISBNSelected == null || ISBNSelected.equals("") || !libreria.existeISBN(ISBNSelected)) {
                    JOptionPane.showMessageDialog(null, "ISBN incorrecto", "Error", 0);
                    return;
                }

                int confirmación = JOptionPane.showConfirmDialog(null,
                        "¿Seguro que desea borrar el libro seleccionado? || "
                                + libreria.buscarLibroPorISBN(ISBNSelected).toString(),
                        "Borrar libro", JOptionPane.YES_NO_OPTION, 3);
                if (confirmación == 1) {
                    JOptionPane.showMessageDialog(null, "Cancelado", "Error", 0);
                    return;
                }

                libreria.removeLibro(ISBNSelected);
                rellenarTabla(libreria);
            }
        });

        btnModificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String ISBNSelected = JOptionPane.showInputDialog(panel, "Introduce el ISBN del libro a modificar");
                if (ISBNSelected == null || ISBNSelected.equals("") || !libreria.existeISBN(ISBNSelected)) {
                    JOptionPane.showMessageDialog(null, "ISBN incorrecto", "Error", 0);
                    return;
                }

                String libroString = libreria.buscarLibroPorISBN(ISBNSelected).toString();

                String[] options = { "Título", "Autor", "Editorial", "Precio", "Formato", "Edición" };
                int decisión = JOptionPane.showOptionDialog(panel, libroString + "\n¿Qué campo quieres modificar?",
                        "Modificar Libro", 0, JOptionPane.PLAIN_MESSAGE, null, options, null);

                String campo = options[decisión];
                String nuevoValor = JOptionPane.showInputDialog(panel, "¿Cuál es el nuevo valor para " + campo + "?");

                if (nuevoValor == null || nuevoValor.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Valor no válido", "Error", 0);
                    return;
                }

                boolean éxito = libreria.modificarLibro(ISBNSelected, campo, nuevoValor);
                if (!éxito) {
                    if (campo.toLowerCase().equals("formato")) {
                        JOptionPane.showMessageDialog(null,
                                "Error al modificar el formato. Elige Cartoné, Rústica, Grapada o Espiral.", "Error",
                                0);
                    } else if (campo.toLowerCase().equals("edición")) {
                        JOptionPane.showMessageDialog(null, "Error al modificar la edición. Elige Reedición o Novedad.",
                                "Error", 0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar el libro.", "Error", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Libro modificado con éxito.", "Modificación exitosa", 1);
                    rellenarTabla(libreria);
                }
            }
        });

        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ISBNSeleccionado = JOptionPane.showInputDialog(frame,
                        "Introduce el ISBN del libro que quieres comprar.", "Compra de libro", 1);

                if (ISBNSeleccionado == null || ISBNSeleccionado.equals("") || !libreria.existeISBN(ISBNSeleccionado)) {
                    JOptionPane.showMessageDialog(null, "ISBN incorrecto", "Error", 0);
                    return;
                }
                Libro libro = libreria.buscarLibroPorISBN(ISBNSeleccionado);
                if (libro == null) {
                    JOptionPane.showMessageDialog(null, "No se pudo encontrar el libro.", "Error", 0);
                }

                String libroString = libro.toString();

                String cantidadStr = JOptionPane.showInputDialog(frame,
                        libroString + "\nIntroduce la cantidad de libros que quieres comprar.", "Cantidad de compra",
                        1);

                if (cantidadStr == null || cantidadStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida", "Error", 0);
                    return;
                }

                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(null, "Cantidad no válida", "Error", 0);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida", "Error", 0);
                    return;
                }

                float costeTotal = libro.getPrecio() * cantidad;
                int confirmación = JOptionPane
                        .showConfirmDialog(
                                frame, "Estás a punto de comprar " + cantidad + " libro(s) por un total de "
                                        + costeTotal + "€. ¿Estás seguro?",
                                "Confirmar compra", JOptionPane.YES_NO_OPTION);

                if (confirmación == JOptionPane.YES_OPTION) {
                    libro.setCantidad(libro.getCantidad() + cantidad);
                    JOptionPane.showMessageDialog(null, "Compra exitosa. Nueva cantidad: " + libro.getCantidad(),
                            "Compra realizada", JOptionPane.INFORMATION_MESSAGE);
                    rellenarTabla(libreria);
                } else {
                    JOptionPane.showMessageDialog(null, "Compra cancelada", "Compra cancelada",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ISBNSelected = JOptionPane.showInputDialog(frame,
                        "Introduce el ISBN del libro que quieres vender.", "Venta de libro", 1);

                if (ISBNSelected == null || ISBNSelected.equals("") || !libreria.existeISBN(ISBNSelected)) {
                    JOptionPane.showMessageDialog(null, "ISBN incorrecto", "Error", 0);
                    return;
                }

                Libro libro = libreria.buscarLibroPorISBN(ISBNSelected);
                if (libro == null) {
                    JOptionPane.showMessageDialog(null, "No se pudo encontrar el libro.", "Error", 0);
                }

                String libroString = libro.toString();

                String cantidadStr = JOptionPane.showInputDialog(frame,
                        libroString + "\nIntroduce la cantidad de libros que quieres vender.", "Cantidad de venta", 1);

                if (cantidadStr == null || cantidadStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida", "Error", 0);
                    return;
                }

                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(null, "Cantidad no válida", "Error", 0);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida", "Error", 0);
                    return;
                }

                if (cantidad > libro.getCantidad()) {
                    JOptionPane.showMessageDialog(null,
                            "No puedes vender más libros de los que tienes. Cantidad disponible: "
                                    + libro.getCantidad(),
                            "Error", 0);
                    return;
                }

                float gananciaTotal = libro.getPrecio() * cantidad;
                int confirmación = JOptionPane
                        .showConfirmDialog(
                                frame, "Estás a punto de vender " + cantidad + " libro(s) por un total de "
                                        + gananciaTotal + "€. ¿Estás seguro?",
                                "Confirmar venta", JOptionPane.YES_NO_OPTION);

                if (confirmación == JOptionPane.YES_OPTION) {
                    libro.setCantidad(libro.getCantidad() - cantidad);
                    JOptionPane.showMessageDialog(null, "Venta exitosa. Nueva cantidad: " + libro.getCantidad(),
                            "Venta realizada", JOptionPane.INFORMATION_MESSAGE);
                    rellenarTabla(libreria);
                } else {
                    JOptionPane.showMessageDialog(null, "Venta cancelada", "Venta cancelada",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });

    }

    /**
     * Devuelve la opción seleccionada de un grupo de RadioButtons que se encuentran
     * en un ButtonGroup
     * 
     * @param grupo - El grupo donde se encuentra los RadioButtons.
     * @return El texto del botón seleccionado
     */
    private String getRadioSelect(ButtonGroup grupo) {
        Enumeration<AbstractButton> elem = grupo.getElements();
        for (int i = 0; i < grupo.getButtonCount(); i++) {
            AbstractButton act = elem.nextElement();
            if (act.isSelected())
                return act.getText();
        }
        return "";

    }

    /**
     * Añadir una serie de RadioButtons a un ButtonGroup
     * 
     * @param panel - El panel donde se encuentran los RadioButtons
     * @return ButtonGroup con los RadioButtons contenidos en él
     */
    private ButtonGroup addRadioButtonGroups(JPanel panel) {
        ButtonGroup grupo = new ButtonGroup();
        for (Component c : panel.getComponents()) {
            grupo.add((JRadioButton) c);
        }
        return grupo;

    }

    /**
     * Vacía los campos a rellenar
     */
    private void vaciarCampos() {
        iSBNTextField.setText("");
        tituloTextField.setText("");
        autorTextField.setText("");
        editorialTextField.setText("");
        precioTextField.setText("");
    }

    /**
     * Actualiza la tabla "biblioteca"
     * 
     * @param librería - La librería donde se encuentran los libros
     */
    private void rellenarTabla(Libreria librería) {
        String columnNames[] = { "ISBN", "Título", "Autor", "Editorial", "Precio", "Cantidad", "Formato", "Edición" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(columnNames);
        librería.sortLibreria();

        for (Libro libro : librería.getLibros()) {

            Object[] rowData = { libro.getISBN(), libro.getTitulo(), libro.getAutor(), libro.getEditorial(),
                    libro.getPrecio(), libro.getCantidad(), libro.getFormato(), libro.getEdicion() };

            tableModel.addRow(rowData);
        }

        tableLibros.setModel(tableModel);
    }
}
