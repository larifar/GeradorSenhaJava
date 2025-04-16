package challenge;

import challenge.service.GeradorSenha;
import challenge.service.TypeSenha;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GeradorApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GeradorSenha gerador = new GeradorSenha();

        TextField tamanhoField = new TextField();
        tamanhoField.setPromptText("Tamanho da senha");

        CheckBox specialCheck = new CheckBox("Caracteres especiais");

        ComboBox<TypeSenha> tipoBox = new ComboBox<>();
        tipoBox.getItems().addAll(TypeSenha.CHARACTER, TypeSenha.NUMBER, TypeSenha.BOTH);
        tipoBox.setValue(TypeSenha.BOTH);

        Button gerarBtn = new Button("Gerar Senha");
        TextArea resultado = new TextArea();
        resultado.setEditable(false);

        gerarBtn.setOnAction(e -> {
            int length = Integer.parseInt(tamanhoField.getText());
            boolean special = specialCheck.isSelected();
            TypeSenha tipo = tipoBox.getValue();
            String senha = gerador.gerarSenha(length, tipo, special);
            resultado.setText(senha);
        });

        VBox layout = new VBox(10, tamanhoField, tipoBox, specialCheck, gerarBtn, resultado);
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Gerador de Senhas");
        stage.show();

    }
}
