import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author ali
 */
public class DrawSierpinsiki extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas c = new Canvas(1000, 800);
        primaryStage.setResizable(false);
        GraphicsContext gc = c.getGraphicsContext2D();
        triangle(0,c.getHeight(),600,gc,10);
        primaryStage.setScene(new Scene(new Pane(c)));
        primaryStage.show();
    }

    static void triangle(double x, double y, double l,GraphicsContext gc, int n){
        double[] posX = new double[]{x, x+l/2, x + l};
        double[] posY = new double[]{y, y-l, y};
        gc.strokePolygon(posX,posY,3 );
        l /= 2;
        if (n==0){
            return;
        }
            triangle(x,y,l,gc,n-1);
            triangle(x+l,y,l,gc,n-1);
            triangle(x+l/2,y-l,l,gc,n-1);

    }
}
