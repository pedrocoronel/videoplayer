import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.io.File;

public class VideoPlayer extends Application {

    private Scene cenaInicial;
    private Scene cenaVideo;
    private MediaPlayer mediaPlayer;
    private Button botaoPlay;
    private Button botaoPause;
    private Button botaoReiniciar;
    private Button botaoSair;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Botões para a tela inicial
        Button botaoPlayInicial = new Button("Play");
        Button botaoSairInicial = new Button("Exit");

        // Configurações dos botões
        botaoPlayInicial.setOnAction(e -> mostrarCenaVideo(primaryStage));
        botaoSairInicial.setOnAction(e -> System.exit(0));

        // Layout para a tela inicial
        HBox layoutInicial = new HBox(10, botaoPlayInicial, botaoSairInicial);
        layoutInicial.setAlignment(Pos.CENTER);
        layoutInicial.setStyle("-fx-background-color: black;");

        cenaInicial = new Scene(layoutInicial, 600, 400);

        // Inicializa os botões para a tela de vídeo
        botaoPlay = new Button("Play");
        botaoPause = new Button("Pause");
        botaoReiniciar = new Button("Reiniciar");
        botaoSair = new Button("Sair");

        // Configurações dos botões de vídeo
        botaoPlay.setOnAction(e -> mostrarCenaVideo(primaryStage));
        botaoPause.setOnAction(e -> pausarVideo());
        botaoReiniciar.setOnAction(e -> reiniciarVideo());
        botaoSair.setOnAction(e -> System.exit(0));

        // Layout para os botões de vídeo
        HBox botoesControle = new HBox(10, botaoPlay, botaoPause, botaoReiniciar, botaoSair);
        botoesControle.setAlignment(Pos.BOTTOM_CENTER);

        // Layout para a tela de vídeo
        StackPane layoutVideo = new StackPane();
        layoutVideo.getChildren().addAll(botoesControle);

        cenaVideo = new Scene(layoutVideo, 600, 400);
        cenaVideo.setFill(null); // Define o preenchimento da cena para transparente

        // Mostra a cena inicial
        primaryStage.setScene(cenaInicial);
        primaryStage.setTitle("importantezao");
        primaryStage.show();
    }

    private void mostrarCenaVideo(Stage primaryStage) {
        // Muda para a cena de vídeo ao clicar em "Play"
        primaryStage.setScene(cenaVideo);

        // Aguarda até que o JavaFX esteja totalmente inicializado
        Platform.runLater(() -> {
            try {
                // Substitua o caminho abaixo pelo caminho do seu vídeo
                String videoPath = "nargas.mp4";
                Media media = new Media(new File(videoPath).toURI().toString());

                mediaPlayer = new MediaPlayer(media);
                MediaView mediaView = new MediaView(mediaPlayer);

                // Ajusta o tamanho da visualização do vídeo
                mediaView.setFitWidth(600);
                mediaView.setFitHeight(400);
                mediaView.setPreserveRatio(true);

                // Adiciona a visualização do vídeo ao layout
                StackPane layoutVideo = new StackPane();
                layoutVideo.getChildren().addAll(mediaView);

                // Configura a cena do vídeo
                cenaVideo.setRoot(layoutVideo);

                // Inicia o vídeo
                mediaPlayer.play();
            } catch (Exception e) {
                // Exibe mensagem de erro no console
                e.printStackTrace();
            }
        });
    }

    private void pausarVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    private void reiniciarVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(mediaPlayer.getStartTime());
        }
    }
}
