package challenge;

import challenge.service.GeradorSenha;
import challenge.service.TypeSenha;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GeradorApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GeradorSenha gerador = new GeradorSenha();

        TextField tamanhoField = new TextField();
        tamanhoField.setPromptText("Tamanho da senha");
        tamanhoField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            return newText.matches("\\d*") ? change : null;
        }));

        CheckBox specialCheck = new CheckBox("Caracteres especiais");

        ComboBox<TypeSenha> tipoBox = new ComboBox<>();
        tipoBox.getItems().addAll(TypeSenha.CHARACTER, TypeSenha.NUMBER, TypeSenha.BOTH);
        tipoBox.setValue(TypeSenha.BOTH);

        Button gerarBtn = new Button("Gerar Senha");
        TextArea resultado = new TextArea();
        resultado.setEditable(false);

        Button copiarBtn = getCopyButton(resultado);

        gerarBtn.setOnAction(e -> {
            String input = tamanhoField.getText();
            if (input.isEmpty() || Integer.parseInt(input) < 2) {
                // exibe alerta ou mensagem de erro
                resultado.setText("Digite um número inteiro maior ou igual a 2.");
                return;
            }
            int length = Integer.parseInt(tamanhoField.getText());
            boolean special = specialCheck.isSelected();
            TypeSenha tipo = tipoBox.getValue();

            String senha = gerador.gerarSenha(length, tipo, special);
            changeColor(gerarBtn);
            resultado.setText(senha);
        });

        VBox layout = new VBox(10, tamanhoField, tipoBox, specialCheck, gerarBtn, resultado, copiarBtn);
        Scene scene = new Scene(layout, 300, 250);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Gerador de Senhas");
        stage.show();

    }

    private Button getCopyButton(TextArea resultado) {
        Button copiarBtn = new Button("Copiar senha");
        copiarBtn.setOnAction(e -> {
            String senha = resultado.getText();
            if (!senha.isEmpty()) {
                ClipboardContent content = new ClipboardContent();
                content.putString(senha);
                Clipboard.getSystemClipboard().setContent(content);

                this.changeColor(copiarBtn);
            }
        });
        return copiarBtn;
    }

    private void changeColor(Button button){
        button.setStyle("-fx-background-color: lightgreen");

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(0.265),
                evt -> button.setStyle("")
        ));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
